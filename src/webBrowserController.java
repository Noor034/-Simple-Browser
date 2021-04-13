import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class webBrowserController implements Initializable {
    
    @FXML
    private TextField urlSearchBar ;
    
    @FXML
    private Button urlSearButton ;
    
    @FXML
    private TextField contentSearchBar ;
    
    @FXML
    private Button contentSearButton ;
    
    @FXML
    private Button forwardButton ;
    
    @FXML
    private Button backButton ;
    
    @FXML
    private Button reloadButton ;
    
    @FXML
    private Button historyButton ;
    
    @FXML
    private Button backFromHistoryButton ;
    
    @FXML
    private TextArea historyTextArea ;
    @FXML
    private WebView web ;    
    WebEngine engine ;  
    ObservableList<WebHistory.Entry> historyEntryList;
    WebHistory history ;
    FileWriter fileWriter ;    
    @FXML
    public void showHistory(ActionEvent event) throws IOException{
        Parent newRoot = FXMLLoader.load(getClass().getResource("histor.fxml")) ;
        Scene newScene = new Scene(newRoot) ;
        
        Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;

        newStage.setMaximized(true);
        
        newStage.setScene(newScene);
        newStage.show();
        
    }    
    public void googleSearch(){
        engine.load("https://www.google.com/");
    }
    
    public void youtubeSearch(){
        engine.load("https://www.youtube.com/");
    }    
    public void facebookSearch(){
        engine.load("https://www.facebook.com/");
    }    
    public void yahooSearch(){
        engine.load("https://www.yahoo.com/");
    }    
    public void wikipediaSearch(){
        engine.load("https://www.wikipedia.org/");
    }    
    public void twitterSearch(){
        engine.load("https://www.twitter.com/");
    }    
    public void amazonSearch(){
        engine.load("https://www.amazon.com/");
    }    
    public void urlSearch(){
        String httLink = "https://" ;
        engine.load(httLink + urlSearchBar.getText());
    }
    public void contentSearch(){
        String s1 = contentSearchBar.getText();
        String httLink = "https://" ;
        engine.load(httLink+"www.google.com/search?q="+s1);
    }   
    public void goBack() {
	history.go(historyEntryList.size() > 1 && history.getCurrentIndex() > 0 ? -1 : 0);
    }    
    public void goForward() {
	history.go(historyEntryList.size() > 1 && history.getCurrentIndex() < historyEntryList.size() - 1 ? 1 : 0);
    }    
    public void noting(){
        System.out.println("DDD");
    }    
    public void reload(){
        if (history.getEntries().isEmpty())
		engine.load("about:home");
	    else
		engine.reload();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");     
    // home-page
        engine = web.getEngine() ;            //returns the WebEngine object
        engine.setJavaScriptEnabled(true);
        engine.load("https://www.google.com/");
    //history
        history = engine.getHistory(); //A single instance of WebHistory for a particular web engine can be obtained through it
        historyEntryList = history.getEntries(); //get te url
        historyEntryList.addListener(new ListChangeListener() {
 
            @Override
            public void onChanged(ListChangeListener.Change change) {
                try {
                    //Returns the string representation of the list
                    String str = String.valueOf(historyEntryList.get(historyEntryList.size() - 1)) ;
                    int lastPoint = str.indexOf(',');
                    String strFinal = str.substring( 6, lastPoint) ;
                    
                    fileWriter = new FileWriter("History.txt", true) ;
                    BufferedWriter writer = new BufferedWriter(fileWriter) ;
                    
                    writer.append(strFinal);
                    writer.newLine();
                    
                    writer.flush();
                    writer.close();
                    fileWriter.close();
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(webBrowserController.class.getName()).log(Level.SEVERE, null, ex);
                
                } catch (IOException ex) {
                    Logger.getLogger(webBrowserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });       
    }
}
