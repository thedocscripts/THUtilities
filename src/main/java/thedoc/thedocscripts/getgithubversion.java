/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thedoc.thedocscripts;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

/**
 *
 * @author thedoc
 */
///
public class getgithubversion {
        /**
     * @param _updtpath Input you github master repo path example: "/thedocscripts/VotyfyVer" this will look automatically for version file
     * @param apppath Input your version path in my case is /version
     */
    public String GetGHVersion(String _updtpath, String apppath) throws IOException, InterruptedException{
        String UpdatePath = _updtpath;
       
        File f = new File(getClass().getResource(apppath).getPath());
       
        // create a client
        Scanner s = new Scanner(f);
        
        String currvers = s.nextLine();
        double currver = Double.parseDouble(currvers);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://raw.githubusercontent.com" + UpdatePath + "/master/version"))
                .build();
        HttpResponse<String> response = client.send(request,
        HttpResponse.BodyHandlers.ofString());

        double intver = Double.parseDouble(response.body());
       
        if(currver < intver){  //if current version is under tell user to update
            return "updt";
        }else if(currver > intver){ //if current version is over the github version notify user
            return "ovrup";
        }else if (currver == intver){ // if current version is on point 
            return "ontime";
        }
       
        return "none";  //if verification fails
    }
    
}
