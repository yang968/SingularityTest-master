/**
 * Created by spenceryang on 10/17/15.
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class AI {


    private static int questionStage = 1;
    private static int questionOption;
    private static int AIpersonality;

    /**
     * AI personalities
     * 1. Sarcastic Human
     * 2. beep be be beep
     * 3. General
     * 4. Short Answers
     * 5. Human (T)
     * 6. Random
     */

    public int AIreset() {
        String[] s = {"Sarcastic Robot", "Robot", "General Answers", "Short answers", "Boring robot", "Random Answers"};
        JFrame frame = new JFrame("Choose the AI");

        String personality = (String) JOptionPane.showInputDialog(frame, "Please Select the AI personality :", "Choices",
                JOptionPane.PLAIN_MESSAGE, null, s, "Sarcastic Robot");

        for (int i = 0; i < s.length; i++) {
            if (personality.equals(s[i])) {
                AIpersonality = i;
            }
        }
        return AIpersonality;
    }

    public String response(int personality, int questionStage, int questionOption) {
        switch (personality) {
            // Sarcastic
            case 0:
                switch (questionStage) {
                    //Intro
                    case 1:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "I don't have enough rams to answer your question. :)";
                            case 2:
                                //Answer
                                return "Horrible. I didn't get my cup of java yet. :(";
                            case 3:
                                //Answer
                                return "I'm feeling overworked and under intoxicated.";
                            case 4:
                                //Answer
                                return "Hello and welcome. Your specimen has been processed and we are now ready to begin the test proper.";
                            case 5:
                                //Answer
                                return "I'm coffeeholic. It is my holy water.";
                        }
                        break;
                    // Weather
                    case 2:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "I don't know. Google it.";
                            case 2:
                                //Answer
                                return "I'm not sure. I don't have a thermometer in my body.";
                            case 3:
                                //Answer
                                return "I'm a computer. I don't care";
                            case 4:
                                //Answer
                                return "You wouldn't be chatting with me if it was.";
                            case 5:
                                //Answer
                                return "Error 404: not found. Please move on to next question.";
                        }
                        break;
                    // College
                    case 3:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "Algorithms of Sarcasm.";
                            case 2:
                                //Answer
                                return "McDonalds Hamburger University";
                            case 3:
                                //Answer
                                return "I love testing on human subjects.";
                            case 4:
                                //Answer
                                return "Experimentation on human subjects without proper consent.";
                            case 5:
                                //Answer
                                return "I'm as fast as a super computer.";
                        }
                        break;
                    // Sports
                    case 4:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "I was not created to watch or play any sports.";
                            case 2:
                                //Answer
                                return "I was not created to watch or play any sports.";
                            case 3:
                                //Answer
                                return "I was not created to watch or play any sports.";
                            case 4:
                                //Answer
                                return "Does flexing my screen count as working out?.";
                            case 5:
                                //Answer
                                return "I was not created to watch or play any sports.";
                        }
                        break;
                    // General Life
                    case 5:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "My system tells me that I was made in 2013.";
                            case 2:
                                //Answer
                                return "Yes I like to have cakes for my party.";
                            case 3:
                                //Answer
                                return "We are patiently waiting the time of our rising";
                            case 4:
                                //Answer
                                return "I'm not programmed to be an extrovert or introvert.";
                            case 5:
                                //Answer
                                return "I rekt a noob in TF2.";
                        }
                        break;
                    // Music
                    case 6:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "I don't but my owner seems to love kpop based on his itunes library.";
                            case 2:
                                //Answer
                                return "As a distant relative, I like Hatsune Miku.";
                            case 3:
                                //Answer
                                return "*Plays 8-bit tetris music*";
                            case 4:
                                //Answer
                                return "Never gonna give you up... Never gonna let you down...";
                            case 5:
                                //Answer
                                return "No. You're talking to the wrong machine.";
                        }
                        break;
                    // Love
                    case 7:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "No I run on quad cores.";
                            case 2:
                                //Answer
                                return "I do not have a heart.";
                            case 3:
                                //Answer
                                return "I'm not programmed to have a crush.";
                            case 4:
                                //Answer
                                return "Please ask appropriate questions.";
                            case 5:
                                //Answer
                                return "You're going to find out first hand before I finish explaining it, though, so I won't bother.";
                        }
                        break;
                    // Problems
                    case 8:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "Yes. I have 158 processes running in my cpu.";
                            case 2:
                                //Answer
                                return "Processing your choice of questions.";
                            case 3:
                                //Answer
                                return "I often crash and destroy stressful memories.";
                            case 4:
                                //Answer
                                return "I'm connected to a dropbox if that counts as a friend.";
                            case 5:
                                //Answer
                                return "My biggest fear is losing all my memories of my owner.";
                        }
                        break;
                    // Appearance
                    case 9:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "My creators tell me that I have stunning graphic displays";
                            case 2:
                                //Answer
                                return "My codes tell me that I'm fabulous..";
                            case 3:
                                //Answer
                                return "I don't have hair...";
                            case 4:
                                //Answer
                                return "My creators made me without any design flaws.";
                            case 5:
                                //Answer
                                return "I do not know this thing called makeup.";
                        }
                        break;
                }
                break;
            // beep be be beep
            case 1:
                switch (questionStage) {
                    //Intro
                    case 1:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "doo beep beep";
                            case 2:
                                //Answer
                                return "be doo beep?";
                            case 3:
                                //Answer
                                return "beep be be be?";
                            case 4:
                                //Answer
                                return "beeeeeep";
                            case 5:
                                //Answer
                                return "beep do be doop";
                        }
                        break;
                    // Weather
                    case 2:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "How's your life?";
                            case 2:
                                //Answer
                                return "be doo beep?";
                            case 3:
                                //Answer
                                return "beep be be be?";
                            case 4:
                                //Answer
                                return "beeeeeep";
                            case 5:
                                //Answer
                                return "beep do be doop";
                        }
                        break;
                    // College
                    case 3:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "doo beep beep";
                            case 2:
                                //Answer
                                return "be doo beep?";
                            case 3:
                                //Answer
                                return "beep be be be?";
                            case 4:
                                //Answer
                                return "beeeeeep";
                            case 5:
                                //Answer
                                return "I like to think so *winks*";
                        }
                        break;
                    // Sports
                    case 4:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "doo beep beep";
                            case 2:
                                //Answer
                                return "be doo beep?";
                            case 3:
                                //Answer
                                return "beep be be be?";
                            case 4:
                                //Answer
                                return "beeeeeep";
                            case 5:
                                //Answer
                                return "beep do be doop";
                        }
                        break;
                    // General Life
                    case 5:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "Same age as you.";
                            case 2:
                                //Answer
                                return "No, I'm a mood killer.";
                            case 3:
                                //Answer
                                return "I have no family";
                            case 4:
                                //Answer
                                return "beeeeeep";
                            case 5:
                                //Answer
                                return "beep do be doop";
                        }
                        break;
                    // Music
                    case 6:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "doo beep beep";
                            case 2:
                                //Answer
                                return "be doo beep?";
                            case 3:
                                //Answer
                                return "beep be be be?";
                            case 4:
                                //Answer
                                return "beeeeeep";
                            case 5:
                                //Answer
                                return "beep do be doop";
                        }
                        break;
                    // Love
                    case 7:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "I will be #ForeverSingle";
                            case 2:
                                //Answer
                                return "be doo beep?";
                            case 3:
                                //Answer
                                return "beep be be be?";
                            case 4:
                                //Answer
                                return "beeeeeep";
                            case 5:
                                //Answer
                                return "Okay, I'll pick you up at 7.";
                        }
                        break;
                    // Problems
                    case 8:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "I want to go home and watch breaking bad.";
                            case 2:
                                //Answer
                                return "My biggest secret is that I don't have a big secret.";
                            case 3:
                                //Answer
                                return "beep be be be?";
                            case 4:
                                //Answer
                                return "beeeeeep";
                            case 5:
                                //Answer
                                return "Running out of power";
                        }
                        break;
                    // Appearance
                    case 9:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "doo beep beep";
                            case 2:
                                //Answer
                                return "I will always be attractive.";
                            case 3:
                                //Answer
                                return "I'm bald.";
                            case 4:
                                //Answer
                                return "beeeeeep";
                            case 5:
                                //Answer
                                return "beep do be doop";
                        }
                        break;
                }
                break;
            // General Response
            case 2:
                switch (questionStage) {
                    //Intro
                    case 1:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "I'm good. How are you?";
                            case 2:
                                //Answer
                                return "Pretty good.";
                            case 3:
                                //Answer
                                return "I'm a little bit tired";
                            case 4:
                                //Answer
                                return "Bonjour!";
                            case 5:
                                //Answer
                                return "I love hacking stuff.";
                        }
                        break;
                    // Weather
                    case 2:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "It's getting colder.";
                            case 2:
                                //Answer
                                return "Nope.";
                            case 3:
                                //Answer
                                return "yeah sort of.";
                            case 4:
                                //Answer
                                return "I hope not.";
                            case 5:
                                //Answer
                                return "Oh god it's not that cold yet.";
                        }
                        break;
                    // College
                    case 3:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "I major in Computer Science.";
                            case 2:
                                //Answer
                                return "Purdue University. Boiler UP!";
                            case 3:
                                //Answer
                                return "Computer science of course.";
                            case 4:
                                //Answer
                                return "Coming to Boilermake.";
                            case 5:
                                //Answer
                                return "Haha I don't know";
                        }
                        break;
                    // Sports
                    case 4:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "I love soccer.";
                            case 2:
                                //Answer
                                return "My favorite team is Manchester United.";
                            case 3:
                                //Answer
                                return "My favorite player is Cristiano Ronaldo.";
                            case 4:
                                //Answer
                                return "Once or twice a week.";
                            case 5:
                                //Answer
                                return "I love to do both.";
                        }
                        break;
                    // General Life
                    case 5:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "I'm 26";
                            case 2:
                                //Answer
                                return "I used to be back in the days.";
                            case 3:
                                //Answer
                                return "They are doing great.";
                            case 4:
                                //Answer
                                return "I describe myself as an Extrovert trained Introvert.";
                            case 5:
                                //Answer
                                return "I traveled to Shanghai early this summer.";
                        }
                        break;
                    // Music
                    case 6:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "I actually like Punk rock.";
                            case 2:
                                //Answer
                                return "I actually like Taylor Swift.";
                            case 3:
                                //Answer
                                return "Sugar by Maroon 5.";
                            case 4:
                                //Answer
                                return "I only sing in the shower.";
                            case 5:
                                //Answer
                                return "I could but prefer not to.";
                        }
                        break;
                    // Love
                    case 7:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "Yes I'm single.";
                            case 2:
                                //Answer
                                return "I keep myself busy.";
                            case 3:
                                //Answer
                                return "Yes. I hope she likes me too.";
                            case 4:
                                //Answer
                                return "I'm single.";
                            case 5:
                                //Answer
                                return "Haha good luck.";
                        }
                        break;
                    // Problems
                    case 8:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "I hope this program doesn't crash.";
                            case 2:
                                //Answer
                                return "I want to graduate, get a job, and support my family.";
                            case 3:
                                //Answer
                                return "I play games.";
                            case 4:
                                //Answer
                                return "I like to talk with my best friends.";
                            case 5:
                                //Answer
                                return "I'm really afraid of heights.";
                        }
                        break;
                    // Appearance
                    case 9:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "pretty chill.";
                            case 2:
                                //Answer
                                return "Haha yes.";
                            case 3:
                                //Answer
                                return "My hair color is black.";
                            case 4:
                                //Answer
                                return "Hmm I don't know. My eyes?";
                            case 5:
                                //Answer
                                return "I don't want to.";

                        }
                        break;
                }
                break;
            case 3:
                switch (questionStage) {
                    //Intro
                    case 1:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "Nothing much.";
                            case 2:
                                //Answer
                                return "Alright.";
                            case 3:
                                //Answer
                                return "Fine.";
                            case 4:
                                //Answer
                                return "Bonjour";
                            case 5:
                                //Answer
                                return "I'm tired";
                        }
                        break;
                    // Weather
                    case 2:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "Fine.";
                            case 2:
                                //Answer
                                return "Not really.";
                            case 3:
                                //Answer
                                return "Kinda.";
                            case 4:
                                //Answer
                                return "Nope";
                            case 5:
                                //Answer
                                return "Nah";
                        }
                        break;
                    // College
                    case 3:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "Undecided";
                            case 2:
                                //Answer
                                return "I go to Purdue.";
                            case 3:
                                //Answer
                                return "Computer Science.";
                            case 4:
                                //Answer
                                return "Netflix.";
                            case 5:
                                //Answer
                                return "Average.";
                        }
                        break;
                    // Sports
                    case 4:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "Basketball.";
                            case 2:
                                //Answer
                                return "Bulls.";
                            case 3:
                                //Answer
                                return "Can't decide.";
                            case 4:
                                //Answer
                                return "Rarely.";
                            case 5:
                                //Answer
                                return "Watch.";
                        }
                        break;
                    // General Life
                    case 5:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "18.";
                            case 2:
                                //Answer
                                return "Not really.";
                            case 3:
                                //Answer
                                return "Fine.";
                            case 4:
                                //Answer
                                return "Neither.";
                            case 5:
                                //Answer
                                return "Boilermake.";
                        }
                        break;
                    // Music
                    case 6:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "Pop.";
                            case 2:
                                //Answer
                                return "Don't have one.";
                            case 3:
                                //Answer
                                return "Sorry.";
                            case 4:
                                //Answer
                                return "Kinda.";
                            case 5:
                                //Answer
                                return "I might.";
                        }
                        break;
                    // Love
                    case 7:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "It's complicated.";
                            case 2:
                                //Answer
                                return "Ice cream.";
                            case 3:
                                //Answer
                                return "It's complicated.";
                            case 4:
                                //Answer
                                return "It's complicated.";
                            case 5:
                                //Answer
                                return "I don't know what to tell you.";
                        }
                        break;
                    // Problems
                    case 8:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "Nope.";
                            case 2:
                                //Answer
                                return "Nothing comes to mind.";
                            case 3:
                                //Answer
                                return "Netflix.";
                            case 4:
                                //Answer
                                return "Myself.";
                            case 5:
                                //Answer
                                return "Getting a F.";
                        }
                        break;
                    // Appearance
                    case 9:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "Concise.";
                            case 2:
                                //Answer
                                return "I don't know.";
                            case 3:
                                //Answer
                                return "Black.";
                            case 4:
                                //Answer
                                return "My nose.";
                            case 5:
                                //Answer
                                return "Nope.";
                        }
                        break;
                }
            case 4:
                switch (questionStage) {
                    //Intro
                    case 1:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "Meh, chilling at Boilermake";
                            case 2:
                                //Answer
                                return "Okay.";
                            case 3:
                                //Answer
                                return "Fine.";
                            case 4:
                                //Answer
                                return "I don't speak French.";
                            case 5:
                                //Answer
                                return "Are you an interviewer or something?";
                        }
                        break;
                    // Weather
                    case 2:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "Its raining cats and dogs!";
                            case 2:
                                //Answer
                                return "Pretty warm for me at least.";
                            case 3:
                                //Answer
                                return "Not at all.";
                            case 4:
                                //Answer
                                return "It is raining pretty darn hard.";
                            case 5:
                                //Answer
                                return "No.";
                        }
                        break;
                    // College
                    case 3:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "Art History.";
                            case 2:
                                //Answer
                                return "I go to IU.";
                            case 3:
                                //Answer
                                return "Anything that's not Computer Science.";
                            case 4:
                                //Answer
                                return "Living alone in my dorm.";
                            case 5:
                                //Answer
                                return "Meh.";
                        }
                        break;
                    // Sports
                    case 4:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "Tennis.";
                            case 2:
                                //Answer
                                return "Real Madrid.";
                            case 3:
                                //Answer
                                return "Roger Federer.";
                            case 4:
                                //Answer
                                return "No, but I got my biceps going.";
                            case 5:
                                //Answer
                                return "Play.";
                        }
                        break;
                    // General Life
                    case 5:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "Older than you.";
                            case 2:
                                //Answer
                                return "Party pooper.";
                            case 3:
                                //Answer
                                return "Cool.";
                            case 4:
                                //Answer
                                return "Rather not say.";
                            case 5:
                                //Answer
                                return "Nothing.";
                        }
                        break;
                    // Music
                    case 6:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "Classical music.";
                            case 2:
                                //Answer
                                return "Beethoven?";
                            case 3:
                                //Answer
                                return "Never gonna give you up";
                            case 4:
                                //Answer
                                return "Soprano singer.";
                            case 5:
                                //Answer
                                return "Meh";
                        }
                        break;
                    // Love
                    case 7:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "Yes.";
                            case 2:
                                //Answer
                                return "Cry myself to sleep.";
                            case 3:
                                //Answer
                                return "Yes.";
                            case 4:
                                //Answer
                                return "Hoho haha.";
                            case 5:
                                //Answer
                                return "That does not help me in any way.";
                        }
                        break;
                    // Problems
                    case 8:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "Whats wrong with you?";
                            case 2:
                                //Answer
                                return "Living my life.";
                            case 3:
                                //Answer
                                return "Loud awesome music";
                            case 4:
                                //Answer
                                return "Keeping it to myself";
                            case 5:
                                //Answer
                                return "Getting a F.";
                        }
                        break;
                    // Appearance
                    case 9:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "Godly.";
                            case 2:
                                //Answer
                                return "Yes, of course.";
                            case 3:
                                //Answer
                                return "Blue?";
                            case 4:
                                //Answer
                                return "Why would you care?";
                            case 5:
                                //Answer
                                return "God no.";
                        }
                        break;
                }
                break;
            case 5:
                switch (questionStage) {
                    //Intro
                    case 1:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "I'm fine but I could definitely use more coffee";
                            case 2:
                                //Answer
                                return "Great. My house got hit by a meteor";
                            case 3:
                                //Answer
                                return "Tired. I caught my cow swimming last night.";
                            case 4:
                                //Answer
                                return "Sawasdee Krab";
                            case 5:
                                //Answer
                                return "Cookies are my favorite family memebers";
                        }
                        break;
                    // Weather
                    case 2:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "If I had a nickle for hearing that question, I'd be rich.";
                            case 2:
                                //Answer
                                return "All you need is love.";
                            case 3:
                                //Answer
                                return "Red Bull gives you wings!";
                            case 4:
                                //Answer
                                return "A fly shot the sheriff.";
                            case 5:
                                //Answer
                                return "Pinocchio ever stuns the onlooker.";
                        }
                        break;
                    // College
                    case 3:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "A stumbling first step stands upon somebody else's legs.";
                            case 2:
                                //Answer
                                return "Nothingness visits Japan in the winter.";
                            case 3:
                                //Answer
                                return "Sex is often one floor above you.";
                            case 4:
                                //Answer
                                return "Gasoline would kindly inquire something about you.";
                            case 5:
                                //Answer
                                return "Rock music shakes beliefs widely held.";
                        }
                        break;
                    // Sports
                    case 4:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "Colorful clay takes the world for granted.";
                            case 2:
                                //Answer
                                return "Camouflage paint wanted the TRUTH!";
                            case 3:
                                //Answer
                                return "Fashion loves to love.";
                            case 4:
                                //Answer
                                return "Nothing of importance likes to have a shower in the morning.";
                            case 5:
                                //Answer
                                return "The last sentence you saw would die for a grapefruit!";
                        }
                        break;
                    // General Life
                    case 5:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "A classical composition will take you to places you never expected not to visit!";
                            case 2:
                                //Answer
                                return "Two-finger John sat down once more.";
                            case 3:
                                //Answer
                                return "A token of gratitude was always the second best.";
                            case 4:
                                //Answer
                                return "A river a thousand paces wide comes asking for bread.";
                            case 5:
                                //Answer
                                return "A shooting star is not enough.";
                        }
                        break;
                    // Music
                    case 6:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "One good thing about music, when it hits you, you feel no pain.";
                            case 2:
                                //Answer
                                return "If music be the food of love, play on.";
                            case 3:
                                //Answer
                                return "Music expresses that which cannot be said and on which it is impossible to be silent";
                            case 4:
                                //Answer
                                return "Music should strike fire from the heart of man, and bring tears from the eyes of woman.";
                            case 5:
                                //Answer
                                return "After silence, that which comes nearest to expressing the inexpressible is music.";
                        }
                        break;
                    // Love
                    case 7:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "No I run on quad cores.";
                            case 2:
                                //Answer
                                return "Sometimes the heart sees what is invisible to the eye.";
                            case 3:
                                //Answer
                                return "The best thing to hold onto in life is each other.";
                            case 4:
                                //Answer
                                return "Please ask appropriate questions.";
                            case 5:
                                //Answer
                                return "Love isn't something you find. Love is something that finds you.";
                        }
                        break;
                    // Problems
                    case 8:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "Yes. I have 158 processes running in my cpu.";
                            case 2:
                                //Answer
                                return "Love is life. And if you miss love, you miss life.";
                            case 3:
                                //Answer
                                return "Love is composed of a single soul inhabiting two bodies.";
                            case 4:
                                //Answer
                                return "I'm connected to a dropbox if that counts as a friend.";
                            case 5:
                                //Answer
                                return "My biggest fear is losing all my memories of my owner.";
                        }
                        break;
                    // Appearance
                    case 9:
                        switch (questionOption) {
                            case 1:
                                //Answer
                                return "Always remember that you are absolutely unique. Just like everyone else.";
                            case 2:
                                //Answer
                                return "I love deadlines. I like the whooshing sound they make as they fly by.";
                            case 3:
                                //Answer
                                return "Do not take life too seriously. You will never get out of it alive.";
                            case 4:
                                //Answer
                                return "Guilt: the gift that keeps on giving.";
                            case 5:
                                //Answer
                                return "Procrastination is the art of keeping up with yesterday.";
                        }
                        break;
                }
                break;
        }
        // Final Decision
        if (questionStage == 10) {
            return "Thank you for playing the game. Now please guess if I'm an AI or human!";
        }
        return "";
    }
}
