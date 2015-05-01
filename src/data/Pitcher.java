/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author sammckay
 */
public class Pitcher {
        
    public String team;
    public String lastName;
    public String firstName;
    public String ip;
    public String er;
    public String w;
    public String sv;
    public String h; 
    public String bb;
    public String k;
    public String notes;
    public String birthYear;
    public String nation;
    
    public Pitcher(){
        team = "0";
        lastName = "0";
        firstName = "0";
        ip = "0";
        er = "0";
        w = "0";
        sv = "0";
        h = "0";
        bb = "0";
        k = "0";
        notes = "0";
        birthYear= "0";
        nation = "0";
    }
    
    public Pitcher(String t, String ln, String fn, String q, String a, String r, String h, String h2, String rb, String s, String n, String by, String nat){
        team = t;
        lastName = ln;
        firstName = fn;
        ip = q;
        er = a;
        w = r;
        sv = h;
        h = h2;
        bb = rb;
        k = s;
        notes = n;
        birthYear = by;
        nation = nat;
    }
    
}