package com.wetrackthensa.chimmy.gatracker;

import java.util.ArrayList;
import java.util.List;

public class priority {
    boolean CBP, CIA, DeptofDefense, FBI, ICE, NSAGov, TheJusticeDept;

    public priority() {
    }

    public void copyPriorities(priority another){
        this.CBP = another.CBP;
        this.CIA = another.CIA;
        this.DeptofDefense = another.DeptofDefense;
        this.FBI = another.FBI;
        this.ICE = another.ICE;
        this.NSAGov = another.NSAGov;
        this.TheJusticeDept = another.TheJusticeDept;
    }

    public priority(priority another) {
        this.CBP = another.CBP;
        this.CIA = another.CIA;
        this.DeptofDefense = another.DeptofDefense;
        this.FBI = another.FBI;
        this.ICE = another.ICE;
        this.NSAGov = another.NSAGov;
        this.TheJusticeDept = another.TheJusticeDept;
    }

    public priority(boolean CBP, boolean CIA, boolean DeptofDefense, boolean FBI, boolean ICE, boolean NSAGov, Boolean TheJusticeDept) {
        this.CBP = CBP;
        this.CIA = CIA;
        this.DeptofDefense = DeptofDefense;
        this.FBI = FBI;
        this.ICE = ICE;
        this.NSAGov = NSAGov;
        this.TheJusticeDept = TheJusticeDept;
    }

    public int howManyTrue(){
        int sum = 0;
        if(CBP) sum++;
        if(CIA) sum++;
        if(DeptofDefense) sum++;
        if(FBI) sum++;
        if(ICE) sum++;
        if(NSAGov) sum++;
        if(TheJusticeDept) sum++;
        return sum;
    }

    public List<String> whatsTrue(){
        List<String> agencyList = new ArrayList<>();
        if(CBP) agencyList.add("CBP");
        if(CIA) agencyList.add("CIA");
        if(DeptofDefense) agencyList.add("DeptofDefense");
        if(FBI) agencyList.add("FBI");
        if(ICE) agencyList.add("ICE");
        if(NSAGov) agencyList.add("NSAGov");
        if(TheJusticeDept) agencyList.add("TheJusticeDept");
        return agencyList;
    }

    public boolean isCBP() {
        return CBP;
    }

    public void setCBP(boolean CBP) {
        this.CBP = CBP;
    }

    public boolean isCIA() {
        return CIA;
    }

    public void setCIA(boolean CIA) {
        this.CIA = CIA;
    }

    public boolean isDeptofDefense() {
        return DeptofDefense;
    }

    public void setDeptofDefense(boolean DeptofDefense) {
        this.DeptofDefense = DeptofDefense;
    }

    public boolean isFBI() {
        return FBI;
    }

    public void setFBI(boolean FBI) {
        this.FBI = FBI;
    }

    public boolean isICE() {
        return ICE;
    }

    public void setICE(boolean ICE) {
        this.ICE = ICE;
    }

    public boolean isNSAGov() {
        return NSAGov;
    }

    public void setNSAGov(boolean NSAGov) {
        this.NSAGov = NSAGov;
    }

    public boolean isTheJusticeDept() {
        return TheJusticeDept;
    }

    public void setTheJusticeDept(boolean TheJusticeDept) {
        this.TheJusticeDept = TheJusticeDept;
    }
}

