package com.example.football;

public class FootBall {

    private String city;
    private String Date;
    private String Team1;
    private String Team2;

    public FootBall(String city,String Date,String Team1,String Team2){
this.city=city;
this.Date=Date;
this.Team1=Team1;
this.Team2=Team2;
    }

    public String getCity() {
        return this.city;
    }
    public String getDate(){
        return this.Date;
    }
    public String getTeam1(){
        return this.Team1;
    }
    public String getTeam2(){
        return this.Team2;
    }
    public void setCity(String city){
        this.city=city;
    }
    public void setDate(String Date){
        this.Date=Date;
    }
    public void setTeam1(String Team1){
        this.Team1=Team1;
    }
    public void setTeam2(String Team2){
        this.Team2=Team2;
    }


    @Override
    public String toString() {
        
        return "City:- "+getCity()+"\n Date :- "+getDate()+"\n Team1 :- "+getTeam1()+"\n Team2 :- "+getTeam2();
    }
}
