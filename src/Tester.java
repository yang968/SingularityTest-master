/**
 * Created by spenceryang on 10/16/15.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A simple Swing-based client for the chat server.  Graphically
 * it is a frame with a text field for entering messages and a
 * textarea to see the whole dialog.
 *
 * The client follows the Chat Protocol which is as follows.
 * When the server sends "SUBMITNAME" the client replies with the
 * desired screen name.  The server will keep sending "SUBMITNAME"
 * requests as long as the client submits screen names that are
 * already in use.  When the server sends a line beginning
 * with "NAMEACCEPTED" the client is now allowed to start
 * sending the server arbitrary strings to be broadcast to all
 * chatters connected to the server.  When the server sends a
 * line beginning with "MESSAGE " then all characters following
 * this string should be displayed in its message area.
 */
public class Tester {

    BufferedReader in;
    PrintWriter out;
    JFrame frame = new JFrame("Tester");
    JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(15, 40);
    public static String userName;
    boolean isFirstLine = true;
    boolean isAIPlaying;
    int questionNumber = 1;
    String lastUserInput;
//    String name;

    /**
     * Constructs the client by laying out the GUI and registering a
     * listener with the textfield so that pressing Return in the
     * listener sends the textfield contents to the server.  Note
     * however that the textfield is initially NOT editable, and
     * only becomes editable AFTER the client receives the NAMEACCEPTED
     * message from the server.
     */
    public Tester() {

        // Layout GUI
        textField.setEditable(false);
        messageArea.setEditable(false);
        frame.getContentPane().add(textField, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.pack();
        frame.setLocationRelativeTo(frame);

        // Add Listeners
        textField.addActionListener(new ActionListener() {
            /**
             * Responds to pressing the enter key in the textfield by sending
             * the contents of the text field to the server.    Then clear
             * the text area in preparation for the next message.
             */
            public void actionPerformed(ActionEvent e) {
                if (!isFirstLine && !textField.getText().equals("")) {
                    if (questionNumber < 10) {
                        if (!(textField.getText().equals("1") || textField.getText().equals("2") || textField.getText().
                                equals("3") || textField.getText().equals("4") || textField.getText().equals("5"))) {
                            JOptionPane.showMessageDialog(frame,
                                    "Please enter a number between 1-5.",
                                    "Input error",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            out.println(textField.getText());
                        }
                    } else {
                        if (!(textField.getText().equals("1") || textField.getText().equals("2"))) {
                            JOptionPane.showMessageDialog(frame,
                                    "Please enter 1 or 2.",
                                    "Input error",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            out.println(textField.getText());
                        }
                    }
                }
                else if (isFirstLine){
                    out.println(textField.getText());
                }
//                if (!isFirstLine) {
//                    if (textField.getText().equals("1") || textField.getText().equals("2") || textField.getText().equals("3") || textField.getText().equals("4") || textField.getText().equals("5")) {
//                        lastUserInput = textField.getText();
//                        questionNumber++;
//                        System.out.println(lastUserInput);
//                    }
//                }
//                else {
//                    lastUserInput = textField.getText();
//                    questionNumber++;
//                    System.out.println(lastUserInput);
//                }
                textField.setText("");
            }
        });
    }

    /**
     * Prompt for and return the address of the server.
     */
    private String getServerAddress() {
        return JOptionPane.showInputDialog(
                frame,
                "Enter IP Address of the Server:",
                "Welcome to the Chatter",
                JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Prompt for and return the desired screen name.
     */
    private String getName() {
        return JOptionPane.showInputDialog(
                frame,
                "Choose a screen name:",
                "Screen name selection",
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Connects to the server then enters the processing loop.
     */
    private void run() throws IOException {

        // Make connection and initialize streams
        String response;
        String serverAddress = getServerAddress();
        Socket socket = new Socket(serverAddress, 8000);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Process all messages from server, according to the protocol.
        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {
                userName = getName();
                out.println(userName);
            } else if (line.startsWith("NAMEACCEPTED")) {
//                userName = getName();
                frame.setTitle(userName);
                textField.setEditable(true);
                messageArea.append("Type Hello To Start the Game\n");
            } else if (line.startsWith("MESSAGE")) {

                messageArea.append(line.substring(8) + "\n");
                if (isFirstLine) {
                    if (line.substring(8, line.lastIndexOf(":")).equals(userName) && line.substring(line.lastIndexOf(" ") + 1).equalsIgnoreCase("hello")) {
                        isFirstLine = false;
                        messageArea.setText("");
                        getQuestionChoices(questionNumber);
                    }
                    else {
                        messageArea.append("Try again\n");
                    }
                } else {
                    if (line.substring(8, line.indexOf(":")).equals("Admin")) {
//                        messageArea.append(line.substring(8) + "\n");
                        JOptionPane.showMessageDialog(frame,
                                line.substring(8),
                                "Response",
                                JOptionPane.INFORMATION_MESSAGE);
                        messageArea.setText("");
                        lastUserInput = line.substring(line.length() - 1);
                        questionNumber++;
                        getQuestionChoices(questionNumber); //the number
                        textField.setEditable(true);
                    }
                    else {
                        textField.setEditable(false);
                        messageArea.append("Waiting for response...\n");
                    }
                }
            }
        }
    }

//    private boolean isAI() {
//        int a = JOptionPane.showConfirmDialog(null,
//                "Should AI play this game?", "Please select",
//                JOptionPane.YES_NO_OPTION);
//        if (a == 1)
//            return false;
//        return true;
//    }
    /**
     * Runs the client as an application with a closeable frame.
     */
    public static void main(String[] args) throws Exception {
        Tester client = new Tester();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.run();
    }

    private void getQuestionChoices(int questionNumber) {
        switch (questionNumber) {
            case 1:
                messageArea.append("Topic: Introduction\n");
                messageArea.append("Option1: What's up?\n");
                messageArea.append("Option2: How is it going?\n");
                messageArea.append("Option3: How are you doing?\n");
                messageArea.append("Option4: Bonjour.\n");
                messageArea.append("Option5: Tell me about yourself.\n");
                break;
            case 2:
                messageArea.append("Topic: Weather\n");
                messageArea.append("Option1: How's the weather?\n");
                messageArea.append("Option2: Is it warm?\n");
                messageArea.append("Option3: Is it cold?\n");
                messageArea.append("Option4: Is it raining?\n");
                messageArea.append("Option5: Is it snowing?\n");
                break;
            case 3:
                messageArea.append("Topic: College\n");
                messageArea.append("Option1: What's your major?\n");
                messageArea.append("Option2: What's the name of your university?\n");
                messageArea.append("Option3: What's your favorite subject?\n");
                messageArea.append("Option4: What was your favorite college experience?\n");
                messageArea.append("Option5: How would you rate your intelligence, relative to your peers?\n");
                break;
            case 4:
                messageArea.append("Topic: Sports\n");
                messageArea.append("Option1: What's your favorite sport?\n");
                messageArea.append("Option2: What's your favorite team?\n");
                messageArea.append("Option3: Whats your favorite player?\n");
                messageArea.append("Option4: How often do you workout?\n");
                messageArea.append("Option5: Do you like to play sports, or would you rather watch sports instead?\n");
                break;
            case 5:
                messageArea.append("Topic: Friends/General life\n");
                messageArea.append("Option1: How old are you?\n");
                messageArea.append("Option2: Are you a party animal?\n");
                messageArea.append("Option3: How is your family?\n");
                messageArea.append("Option4: How would you describe yourself: extrovert or introvert? Why?\n");
                messageArea.append("Option5: What is the coolest thing you have done in the past 6 months.\n");
                break;
            case 6:
                messageArea.append("Topic: Music\n");
                messageArea.append("Option1: What's your favorite music genre?\n");
                messageArea.append("Option2: Who's your favorite artist?\n");
                messageArea.append("Option3: Can you recommend me a song?\n");
                messageArea.append("Option4: Can you sing?\n");
                messageArea.append("Option5: Do you play an instrument\n");
                break;
            case 7:
                messageArea.append("Topic: Love\n");
                messageArea.append("Option1: Are you single?\n");
                messageArea.append("Option2: How do you deal with a heartbreak?\n");
                messageArea.append("Option3: Do you have crush on somebody right now?\n");
                messageArea.append("Option4: Describe your current relationship status.\n");
                messageArea.append("Option5: I want a love advice.\n");
                break;
            case 8:
                messageArea.append("Topic: Problems\n");
                messageArea.append("Option1: Do you have anything in mind right now?\n");
                messageArea.append("Option2: What is the biggest struggle you are trying to overcome?\n");
                messageArea.append("Option3: How do you deal with stress?\n");
                messageArea.append("Option4: Do you always keep everything to yourself, or would you rather talk to a friend when\n" +
                        "faced with conflict?\n");
                messageArea.append("Option5: What's your biggest fear?\n");
                break;
            case 9:
                messageArea.append("Topic: Appearance\n");
                messageArea.append("Option1: What is the one adjective that your friends describes you most?\n");
                messageArea.append("Option2: Do you consider yourself attractive?\n");
                messageArea.append("Option3: What is your hair color?\n");
                messageArea.append("Option4: If you can change one thing about your looks, what would it be?\n");
                messageArea.append("Option5: Have you ever considered putting on makeup?\n");
                break;
            case 10:
                messageArea.append("Decision Time: Human or AI\n");
                messageArea.append("Option1: Human\n");
                messageArea.append("Option2: Ai\n");
                break;
        }
    }
}
