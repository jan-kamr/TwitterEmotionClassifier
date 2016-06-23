//package main.java.classification;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;


public class Serializer {
    private String filename;
    public MultiClassPerceptron model;

    public Serializer(MultiClassPerceptron model, String filename) {
        this.model = model;

        // delete trainingtweets, for saving space (no need to serialize the tweet objects)
        //this.model.trainingtweets = null;
        this.filename = filename;
    }

    public Serializer(String filename) {
        this.filename = filename;
    }

    public void save_model(){
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(this.filename);

            ObjectOutputStream objectOutput = new ObjectOutputStream(outputStream);

            objectOutput.writeObject(this.model);

            objectOutput.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        finally {

            try {
                outputStream.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void load_model(){

        try {
            FileInputStream inputStream = new FileInputStream(this.filename);
            ObjectInputStream objectInput = new ObjectInputStream(inputStream);

            this.model= (MultiClassPerceptron) objectInput.readObject();
        }

        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (this.model == null){
            System.out.println("error in model file");
        }
    }

}
