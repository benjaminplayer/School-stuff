import java.io.*;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JOptionPane;
public class AudioCapture {
    public void captureAudio(String s){
        try {
            String name=checkName(s);
            System.out.println("here!");

            AudioFormat af = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);

            DataLine.Info dataInfo = new DataLine.Info(TargetDataLine.class, af);
            if(!AudioSystem.isLineSupported(dataInfo)){
                System.out.println("Not supported");
            }

            TargetDataLine targetLine = (TargetDataLine)AudioSystem.getLine(dataInfo);
            targetLine.open();

            JOptionPane.showMessageDialog(null, "Hit ok to start!");
            targetLine.start();

            Thread audioRecorderThread = new Thread(){
                @Override public void run(){
                    AudioInputStream ais = new AudioInputStream(targetLine);
                    File f = new File(name+".wav");
                    try {
                        AudioSystem.write(ais, AudioFileFormat.Type.WAVE, f);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Stopped recording");
                }
            };

            audioRecorderThread.start();
            JOptionPane.showMessageDialog(null,"Hit 'ok' to stop recording!");
            targetLine.stop();
            targetLine.close();

 
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void captureAudio(){
        try {
            String name=checkName("");
            System.out.println("here!");

            AudioFormat af = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);

            DataLine.Info dataInfo = new DataLine.Info(TargetDataLine.class, af);
            if(!AudioSystem.isLineSupported(dataInfo)){
                System.out.println("Not supported");
            }

            TargetDataLine targetLine = (TargetDataLine)AudioSystem.getLine(dataInfo);
            targetLine.open();

            JOptionPane.showMessageDialog(null, "Hit ok to start!");
            targetLine.start();

            Thread audioRecorderThread = new Thread(){
                @Override public void run(){
                    AudioInputStream ais = new AudioInputStream(targetLine);
                    File f = new File(name+".wav");
                    try {
                        AudioSystem.write(ais, AudioFileFormat.Type.WAVE, f);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Stopped recording");
                }
            };

            audioRecorderThread.start();
            JOptionPane.showMessageDialog(null,"Hit 'ok' to stop recording!");
            targetLine.stop();
            targetLine.close();

 
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String genName(String name){
        int i = 1;
        while (true) {
            if (name.toUpperCase().charAt(name.length() - 1) >= 'A' && name.toUpperCase().charAt(name.length() - 1) <= 'Z') {
                return name + i;
            } else{
                i = Integer.parseInt(name.charAt(name.length()-1) + "");
                StringBuffer sb = new StringBuffer(name);
                sb.deleteCharAt(sb.length() - 1);
                return name = sb.toString() + (i+1);
                 
            }
        }
    }

    private static String checkName(String name){
        if(name.equals(""));
                name = "untitled";
        try{
            File f = new File(name+".wav");
            while (f.exists()) {
                name = genName(name);
                f=new File(name+".wav");
            }
                
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return name;
    }
}
