package RequestPojo.CreateNewRebateOptionPojo;

import java.util.ArrayList;

public class RebateTermJson {

    private ArrayList rebateTerms;

    public RebateTermJson(ArrayList rebateTerms) {
        this.rebateTerms = rebateTerms;
    }
    public RebateTermJson(){

    }




    public ArrayList getRebateTerms() {
        return rebateTerms;
    }

    public void setRebateTerms(Object rebateTerms) {
        ArrayList<Object> arr=new ArrayList<Object>();
            arr.add(rebateTerms);
            this.rebateTerms = arr;
    }



}