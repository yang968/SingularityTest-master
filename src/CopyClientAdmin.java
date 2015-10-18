/**
 * Created by spenceryang on 10/17/15.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CopyClientAdmin {

    BufferedReader in;
    PrintWriter out;
    JFrame frame = new JFrame("Admin");
    JTextField textField = new JTextField(50);
    JTextArea messageArea = new JTextArea(15, 50);
    boolean isAIPlaying;
    int questionNumber = 1;
    String lastUserInput;
    boolean firstinput = true;
    String testUserName;
    static String userId = "";
    int c = 0;
    AI a;
    int per = 0;
    String response = "";

    /**
     * Constructs the client by laying out the GUI and registering a
     * listener with the textfield so that pressing Return in the
     * listener sends the textfield contents to the server.  Note
     * however that the textfield is initially NOT editable, and
     * only becomes editable AFTER the client receives the NAMEACCEPTED
     * message from the server.
     */
    public CopyClientAdmin() {

        // Layout GUI
        textField.setEditable(false);
        messageArea.setEditable(false);
        frame.getContentPane().add(textField, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.pack();
        frame.setLocationRelativeTo(frame);
        a = new AI();

        // Add Listeners
        textField.addActionListener(new ActionListener() {
            /**
             * Responds to pressing the enter key in the textfield by sending
             * the contents of the text field to the server.    Then clear
             * the text area in preparation for the next message.
             */
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("")) {
                    if (!isAIPlaying) {
                        response = textField.getText();
                        out.println(response);
                        Log.logs.add("Admin: " + response);
                    } else {
                        response = a.response(per ,questionNumber - 1, Integer.parseInt(lastUserInput));
                        out.println(response);
                        Log.logs.add("Admin: " + response);
                    }
                }

                textField.setText("");
                messageArea.setText("");
            }
        });
    }

    /**
     * Prompt for and return the address of the server.
     */
    private String getServerAddress() throws UnknownHostException{
//        return JOptionPane.showInputDialog(
//                frame,
//                "Enter IP Address of the Server:",
//                "Welcome to the Administrator",
//                JOptionPane.QUESTION_MESSAGE);
        return InetAddress.getLocalHost().toString().substring(InetAddress.getLocalHost().toString().indexOf("/") + 1);
    }

    /**
     * Prompt for and return the desired screen name.
     */
    private String getName() {
        return "Admin";
    }

    /**
     * Connects to the server then enters the processing loop.
     */
    private void run() throws IOException {


        // Make connection and initialize streams
        String serverAddress = getServerAddress();
        Socket socket = new Socket(serverAddress, 8000);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Process all messages from server, according to the protocol.
        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {
                out.println(getName());
                this.isAIPlaying = isAI();
                if (isAIPlaying) {
                    per = a.AIreset();
                }
                Log.logs.add("Is AI playing?: " + isAIPlaying);
                messageArea.append("Waiting for tester to start... \n");
            } else if (line.startsWith("NAMEACCEPTED")) {
//                textField.setEditable(true);
            } else if (line.startsWith("MESSAGE")) {
                messageArea.append(line.substring(8) + "\n");
                if (firstinput) {
                    if (line.substring(line.lastIndexOf(" ") + 1).equalsIgnoreCase("hello")) {
                        firstinput = false;
                        testUserName = line.substring(8, line.lastIndexOf(":"));
                        userId = testUserName;
                        //Log.logs.add(testUserName);
//                        textField.setEditable(true);
                    }
                } else {
                    if (questionNumber < 10) {
                        if (line.substring(8, line.lastIndexOf(":")).equals(testUserName)) {
                            if (!firstinput && line.substring(line.length() - 1).equals("1") || line.substring(line.length() - 1).equals("2") ||
                                        line.substring(line.length() - 1).equals("3") || line.substring(line.length() - 1).equals("4") ||
                                        line.substring(line.length() - 1).equals("5")) {
                                lastUserInput = line.substring(line.length() - 1);
                                Log.logs.add(testUserName + ": " + getQuestionChoices(questionNumber,Integer.parseInt(lastUserInput)));
                                messageArea.append(getQuestionChoices(questionNumber, Integer.parseInt(lastUserInput))); //the number
                                questionNumber++;
                                if (isAIPlaying) {
                                    messageArea.append("\n");
                                    messageArea.append("Please pretend you are typing the message below:\n");
                                    messageArea.append(a.response(per, questionNumber - 1, Integer.parseInt(lastUserInput)));
                                }
                                textField.setEditable(true);
                            }
                        } else {
                            textField.setEditable(false);
                            messageArea.append("Admin: " + response + "\n");
                            messageArea.append("Waiting for next question...\n");
                        }
                    }
                    else {
                        if (!firstinput && line.substring(8, line.lastIndexOf(":")).equals(testUserName) &&
                                (line.substring(line.length() - 1).equals("1") || line.substring(line.length() - 1).equals("2"))) {
                            lastUserInput = line.substring(line.length() - 1);
                            Log.logs.add(testUserName + ": " + getQuestionChoices(questionNumber,Integer.parseInt(lastUserInput)));
                            getQuestionChoices(questionNumber, Integer.parseInt(lastUserInput));
                            if (Integer.parseInt(lastUserInput) == 1) {
                                if (isAIPlaying) {
                                    out.println("Sorry your guess is wrong, you've been talking to an AI");
                                    Log.logs.add(testUserName + ": " + "Sorry your guess is wrong, you've been talking to an AI");
                                    messageArea.append("Congratulations. You have successfully tricked your opponent!");
                                    Log.logs.add("Dear Admin, Congratulations. You have successfully tricked your opponent!");
                                    export();
                                    break;
                                }
                                else {
                                    out.println("Yes, you got it right! You've been chatting with a human");
                                    Log.logs.add(testUserName + ": " + "Yes, you got it right! You've been chatting with a human");
                                    messageArea.append("You have failed to trick your opponent :(");
                                    Log.logs.add("Dear Admin, You have failed to trick your opponent :(");
                                    export();
                                    break;
                                }
                            }
                            else {
                                if (isAIPlaying) {
                                    out.println("Yes, you got it right! You've been talking to an AI");
                                    Log.logs.add(testUserName + ": " + "Yes, you got it right! You've been talking to an AI");
                                    messageArea.append("You have failed to trick your opponent :(");
                                    Log.logs.add("Dear Admin, You have failed to trick your opponent :(");
                                    export();
                                    break;
                                }
                                else {
                                    out.println("Sorry your guess is wrong, you've been chatting with a human");
                                    Log.logs.add(testUserName + ": " + "Sorry your guess is wrong, you've been chatting with a human");
                                    messageArea.append("Congratulations. You have successfully tricked your opponent!");
                                    Log.logs.add("Dear Admin, Congratulations! You have successfully tricked your opponent!");
                                    export();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            export();
        }

    }
    private boolean isAI() {
        int a = JOptionPane.showConfirmDialog(null,
                "Should AI play this game?", "Please select",
                JOptionPane.YES_NO_OPTION);
        if (a == 1)
            return false;
        return true;
    }


    /**
     * Runs the client as an application with a closeable frame.
     */
    public static void main(String[] args) throws Exception {
        CopyClientAdmin client = new CopyClientAdmin();
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame,
                "This is the IP Address. Please show it to the Tester before playing: \n" +
                        InetAddress.getLocalHost().toString().substring(InetAddress.getLocalHost().toString().indexOf("/") + 1),
                "IP Address",
                JOptionPane.INFORMATION_MESSAGE);
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.run();
    }
    private String getQuestionChoices(int questionNumber, int lastUserInput) {
        switch (questionNumber) {
            case 1:
//                messageArea.append("Topic: Introduction\n");
                switch (lastUserInput) {
                    case 1: return "Option1: What's up?\n";
                    case 2: return "Option2: How is it going?\n";
                    case 3: return "Option3: How are you doing?\n";
                    case 4: return "Option4: Bonjour.\n";
                    case 5: return "Option5: Tell me about yourself.\n";

                }
                break;
            case 2:
//                messageArea.append("Topic: Weather\n");
                switch (lastUserInput) {
                    case 1: return "Option1: How's the weather?\n";
                    case 2: return "Option2: Is it warm?\n";
                    case 3: return "Option3: Is it cold?\n";
                    case 4: return "Option4: Is it raining?\n";
                    case 5: return "Option5: Is it snowing?\n";
                }
                break;
            case 3:
//                messageArea.append("Topic: College\n");
                switch (lastUserInput) {
                    case 1: return "Option1: What's your major?\n";
                    case 2: return "Option2: What's the name of your university?\n";
                    case 3: return "Option3: What's your favorite subject?\n";
                    case 4: return "Option4: What was your favorite college experience?\n";
                    case 5: return "Option5: How would you rate your intelligence, relative to your peers?\n";
                }
                break;
            case 4:
//                messageArea.append("Topic: Friends/General life\n");

                switch (lastUserInput) {
                    case 1: return "Option1: What's your favorite sport?\n";
                    case 2: return "Option2: What's your favorite team?\n";
                    case 3: return "Option3: Whats your favorite player?\n";
                    case 4: return "Option4: How often do you workout?\n";
                    case 5: return "Option5: Do you like to play sports, or would you rather watch sports instead?\n";
                }
                break;
            case 5:
//                messageArea.append("Topic: Sports\n");

                switch (lastUserInput) {
                    case 1: return "Option1: How old are you?\n";
                    case 2: return "Option2: Are you a party animal?\n";
                    case 3: return "Option3: How is your family?\n";
                    case 4: return "Option4: How would you describe yourself: extrovert or introvert? Why?\n";
                    case 5: return "Option5: What is the coolest thing you have done in the past 6 months.\n";
                }
                break;
            case 6:
//                messageArea.append("Topic: Music\n");

                switch (lastUserInput) {
                    case 1: return "Option1: What's your favorite music genre?\n";
                    case 2: return "Option2: Who's your favorite artist?\n";
                    case 3: return "Option3: Can you recommend me a song?\n";
                    case 4: return "Option4: Can you sing?\n";
                    case 5: return "Option5: Do you play an instrument?\n";
                }
                break;
            case 7:
//                messageArea.append("Topic: Love\n");

                switch (lastUserInput) {
                    case 1: return "Option1: Are you single?\n";
                    case 2: return "Option2: How do you deal with a heartbreak?\n";
                    case 3: return "Option3: Do you have crush on somebody right now?\n";
                    case 4: return "Option4: Describe your current relationship status.\n";
                    case 5: return "Option5: I want a love advice.\n";
                }
                break;
            case 8:
//                messageArea.append("Topic: Problems\n");

                switch (lastUserInput) {
                    case 1: return "Option1: Do you have anything in mind right now?\n";
                    case 2: return "Option2: What is the biggest struggle you are trying to overcome?\n";
                    case 3: return "Option3: How do you deal with stress?\n";
                    case 4: return "Option4: Do you always keep everything to yourself, or would you rather talk to a friend when\n" +
                            "faced with conflict?\n";
                    case 5: return "Option5: What's your biggest fear?\n";
                }
                break;
            case 9:
//                messageArea.append("Topic: Appearance\n");
                switch (lastUserInput) {
                    case 1: return "Option1: What is the one adjective that your friends describes you most?\n";
                    case 2: return "Option2: Do you consider yourself attractive?\n";
                    case 3: return "Option3: What is your hair color?\n";
                    case 4: return "Option4: If you can change one thing about your looks, what would it be?\n";
                    case 5: return "Option5: Have you ever considered putting on makeup?\n";
                }
                break;
            case 10:
                switch (lastUserInput) {
                    case 1: return "Option1: Human\n";
                    case 2: return "Option2: Ai\n";
                }
                break;
        }
        return "";
    }
    public static void export() throws IOException {
        if (!userId.equals("")) {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(userId + ".txt")));
            for (int i = 0; i < Log.logs.size(); i++) {
                out.println(Log.logs.get(i));
            }
            out.close();
        }
    }
}