package com.hr.highpriestess.game.util;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.hr.highpriestess.game.util.Nodes.DialogueNode;
import com.hr.highpriestess.game.util.Nodes.Node;
import com.hr.highpriestess.game.util.Nodes.TweenNode;

import java.io.IOException;


/**
 * Used For Dialogue
 */




public class DialogueUtils {

    static String  TAG = DialogueUtils.class.toString();

    public static void createDialogueNodes(String filename, Array<Node> nodes){
        XmlReader reader = new XmlReader();
        Gdx.app.debug(TAG, "createDialogueNodes called with " + filename);
        try {
            XmlReader.Element root = reader.parse(Gdx.files.internal(filename));
            for (int currentChild = 0; currentChild < root.getChildCount(); currentChild++) {
                XmlReader.Element currentNodeXml = root.getChild(currentChild);

                Integer tag = null;
                if (currentNodeXml.getChildByName("tag").getText() != null) {
                    tag =  Integer.parseInt(currentNodeXml.getChildByName("tag").getText());
                }

                int[] neighbors = null;
                if (currentNodeXml.getChildByName("neighbors").getText() != null) {
                    String[] neighborStrings = currentNodeXml.getChildByName("neighbors").getText().split(", ");
                    neighbors = new int[neighborStrings.length];
                    for (int i = 0; i < neighborStrings.length; i++) {
                        neighbors[i] = Integer.parseInt(neighborStrings[i]);
                    }
                }

                if (currentNodeXml.getChildByName("type").getText().equals("DialogueNode")) {

                    String text = null;
                    if (currentNodeXml.getChildByName("text").getText() != null) {
                        text = (currentNodeXml.getChildByName("text").getText());
                    }

                    String speaker = null;
                    if (currentNodeXml.getChildByName("speaker").getText() != null) {
                        speaker = currentNodeXml.getChildByName("speaker").getText();
                    }


                    Gdx.app.debug(TAG, "neighbors: " + neighbors);
                    DialogueNode newDialogueNode = new DialogueNode(tag, text, speaker, neighbors);
                    nodes.add(newDialogueNode);
                }

                else if (currentNodeXml.getChildByName("type").getText().equals("TweenNode")) {

                    String destination = (currentNodeXml.getChildByName("destination").getText());
                    String actor = currentNodeXml.getChildByName("actor").getText();
                    Boolean stopsDialogue = Boolean.valueOf(currentNodeXml.getChildByName("stopsDialogue").getText());
                    Boolean stopsNextTween = Boolean.valueOf(currentNodeXml.getChildByName("stopsNextTween").getText());

                    TweenNode newTweenNode = new TweenNode(actor, destination, neighbors, stopsDialogue, stopsNextTween);
                    nodes.add(newTweenNode);
                }
            }
        } catch (IOException e)  {
            Gdx.app.debug(TAG, e.toString());
        }

    }

}
