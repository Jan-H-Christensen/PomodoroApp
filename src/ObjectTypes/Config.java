package ObjectTypes;

import Controller.SettingsController;
import PomodoroApp.Main;

import java.io.*;

public class Config {

    private SettingsController setting;

    public Config(SettingsController setting) {
        this.setting = setting;
    }

    public void saveSettings() {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("/config.txt",true));
            //FileOutputStream fos = new FileOutputStream(new File("/config.txt"),true);
            //PrintWriter pw = new PrintWriter(fos);

            //Dark Mode selected
            if(setting.darkMode.isSelected()){
                bw.write("ON");
                bw.flush();
                bw.close();
                System.out.println("on");
            }if(!setting.darkMode.isSelected()){
                bw.write("OFF");
                bw.flush();
                bw.close();
                System.out.println("off");
            }

            //fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void loadSettings(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("/config.txt"));

            //for Dark Mode selected
            String configReader = br.readLine();

            if(configReader.equals("ON")){
                setting.darkMode.setSelected(true);
            }
            if(configReader.equals("OFF")){
                setting.darkMode.setSelected(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
