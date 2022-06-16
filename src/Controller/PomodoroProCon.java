package Controller;

import DB.DBHandler;
import Data.Pomodoro;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
/**
 * @author NIels Rogengell
 * @version 1.0
 * @since 30-5-2022
 */
public class PomodoroProCon extends Controller{

    @FXML
    public Label name,estimateTime,EstimatedPomodoro,status,workTime,breakTime,currentPomodoro;

    @FXML
    public Button btnToDoList,btnProject,btnPomodoro,btnAdmin,btnLogout,btnSettings,btnRemove,btnStart,btnPause,btnResume,btnCancel,btnInterrupt,btnFin;

    @FXML
    public ProgressBar proWork,proBreak;

    @FXML
    public MediaView mediaView;

    private Timeline breakProgress;
    private Timeline workProgress;

    private boolean finishCheck;
    private boolean workRunning;
    private boolean breakRunning;
    private int workProgressCount = 0;
    private int breakProgressCount = 0;

    private MediaPlayer timerMediaPlayer;
    private MediaPlayer endMediaPlayer;
    /**
     * Inside initialize we do teh super initialize
     * Then we bind the labels to the data from the previous labels
     * Then it set all the buttons from the base GUI til disabled, to ensure a more safe proses
     * Than it sets up two timelines for the work progressbar and the break progressbar
     * Than it set up the sound files, so we can use it later on
     */
    @Override
    public void initialize() {
        super.initialize();
        Pomodoro.setCurrentPomodoro("0");
        name.textProperty().bindBidirectional(Pomodoro.namePropertyProperty());
        estimateTime.textProperty().bindBidirectional(Pomodoro.estimateTimePropertyProperty());
        EstimatedPomodoro.textProperty().bindBidirectional(Pomodoro.estimatePomodoroPropertyProperty());
        status.textProperty().bindBidirectional(Pomodoro.statusPropertyProperty());
        workTime.textProperty().bindBidirectional(Pomodoro.workTimePropertyProperty());
        breakTime.textProperty().bindBidirectional(Pomodoro.breakTImePropertyProperty());
        currentPomodoro.textProperty().bindBidirectional(Pomodoro.currentPomodoroPropertyProperty());

        btnToDoList.setDisable(true);
        btnProject.setDisable(true);
        btnPomodoro.setDisable(true);
        btnAdmin.setDisable(true);
        btnLogout.setDisable(true);
        btnSettings.setDisable(true);
        btnRemove.setDisable(true);

        workProgress = new Timeline();
        workProgress.setCycleCount(Animation.INDEFINITE);
        workProgress.setAutoReverse(false);
        workProgress.getKeyFrames().add(new KeyFrame(Duration.minutes(1), event -> {
            int workTime = Integer.parseInt(Pomodoro.getWorkTime());
            double progressIncrease = 1.0/workTime;
            proWork.setProgress(proWork.getProgress()+progressIncrease);
            DBHandler.updateToDoList(proWork.getProgress());
            workProgressCount++;
            if (workProgressCount >= Integer.parseInt(Pomodoro.getWorkTime())){
                endWorkProc();
            }
        }));

        breakProgress = new Timeline();
        breakProgress.setCycleCount(Animation.INDEFINITE);
        breakProgress.setAutoReverse(false);
        breakProgress.getKeyFrames().add(new KeyFrame(Duration.minutes(1), event -> {
            int breakTime = Integer.parseInt(Pomodoro.getBreakTIme());
            double progressIncrease = 1.0/breakTime;
            proBreak.setProgress(proBreak.getProgress()+progressIncrease);
            DBHandler.updateToDoList(proBreak.getProgress()+1.0);
            breakProgressCount++;
            if (breakProgressCount >= Integer.parseInt(Pomodoro.getBreakTIme())){
                endBreakProc();
            }
        }));


        Media timerMedia = new Media(new File("src/SoundFiles/Kitchen_Timer_10_secound.mp3").getAbsoluteFile().toURI().toString());
        timerMediaPlayer = new MediaPlayer(timerMedia);
        timerMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        Media endMedia = new Media(new File("src/SoundFiles/Kitchen_Timer_End.mp3").getAbsoluteFile().toURI().toString());
        endMediaPlayer = new MediaPlayer(endMedia);
        endMediaPlayer.setCycleCount(1);
        endMediaPlayer.setVolume(0.125);

    }
    /**
     * Here we end the work progressbar, updates the database and start the break progressbar
     */
    private void endWorkProc(){
        workProgress.stop();
        workRunning = false;
        proWork.setProgress(1.0);
        DBHandler.updateToDoList(proWork.getProgress(),Integer.parseInt(Pomodoro.getCurrentPomodoro()));
        workProgressCount =0;
        breakProgress.play();
        breakRunning = true;
        if (Pomodoro.isSound()){
            timerMediaPlayer.stop();
            mediaView.setMediaPlayer(endMediaPlayer);
            endMediaPlayer.play();
        }
    }
    /**
     * Here we end the break progressbar, update the database and ready the application to start a new one, finish or cancel
     */
    private void endBreakProc(){
        breakProgress.stop();
        breakRunning = false;
        finishCheck=true;
        proBreak.setProgress(1.0);
        DBHandler.updateToDoList(proBreak.getProgress()+1.0);
        breakProgressCount = 0;
        btnStart.setDisable(false);
        btnCancel.setDisable(false);
        btnFin.setDisable(false);
        endMediaPlayer.stop();
        endMediaPlayer.play();
    }
    /**
     * Here we finish the task and set the task as finished in the database,
     * and change the scene to pomodoroStartScene
     */
    @FXML
    private void finish(){
        if (finishCheck) {
            workRunning = false;
            breakRunning = false;

            if (Integer.parseInt(Pomodoro.getCurrentPomodoro()) > 0) {
                DBHandler.finishPomodoro();
            }
            toDoList.getItems().remove(Pomodoro.getProject());
            Pomodoro.clear();
            proWork.setProgress(0);
            proBreak.setProgress(0);
            btnCancel.setDisable(false);
            this.PomodoroStartScene();
        }
    }
    /**
     * Here we interrupt a progress so work jumps to work end and break to break end
     */
    @FXML
    private void interrupt(){
        if (proWork.getProgress() >= 0 && workRunning){
            endWorkProc();
        }else if (proWork.getProgress() >= 1 && breakRunning){
            endBreakProc();
        }
    }
    /**
     * Here we start the pomodoro
     * First we reset everything
     * Update the current pomodoro
     * disable and enable buttons that are needed
     * Creates an ToDoList
     * Lastly check if the sound should be played
     */
    @FXML
    public void Start(){
        proWork.setProgress(0.0);
        proBreak.setProgress(0.0);
        Pomodoro.setCurrentPomodoro(String.valueOf(Integer.parseInt(currentPomodoro.getText())+1));
        workRunning = true;
        finishCheck=false;
        workProgress.play();
        btnStart.setDisable(true);
        btnCancel.setDisable(true);
        btnFin.setDisable(true);
        DBHandler.createToDoList();
        if (Pomodoro.isSound()){
            endMediaPlayer.stop();
            mediaView.setMediaPlayer(timerMediaPlayer);
            timerMediaPlayer.play();
        }
    }
    /**
     * Here we pause the progress bars, depending on with it is on
     */
    @FXML
    public void Pause(){
        if (workRunning){
            workProgress.stop();
        }
        else if (breakRunning)
        {
            breakProgress.stop();
        }
    }
    /**
     * Here we resume the progress bars, depending on with it is on
     */
    @FXML
    public void Resume(){
        if (workRunning){
            workProgress.play();
        }
        else if (breakRunning)
        {
            breakProgress.play();
        }
    }
    /**
     * Here we cancel the task, updates the taks in database and clear the program for a new task and pomodoro
     */
    @FXML
    public void Cancel(){
        workRunning = false;
        breakRunning = false;
        DBHandler.cancelChecker();
        Pomodoro.clear();
        proWork.setProgress(0);
        proBreak.setProgress(0);
        btnCancel.setDisable(true);
        this.PomodoroStartScene();
    }

    @Override
    public void PomodoroStartScene() {
        super.PomodoroStartScene();
    }
    /**
     * Here we override the checkRank method, so that the admin button does not enable on the scene
     */
    @Override
    public void checkRank() {

    }
}
