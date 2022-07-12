/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.model;

import java.math.BigInteger;

/**
 *
 * @author Minh Hieu Pham
 */
public class KpiMoModel {

    private String datetime;

    private BigInteger moShortcode;

    private BigInteger moReceive;

    private BigInteger moError;

    private BigInteger moPending;

    private BigInteger moFinished;

    private BigInteger moNotifyPending;

    private BigInteger moNotifyFinished;

    private BigInteger moRefund;

    private BigInteger moCharge;

    public KpiMoModel() {
    }

    public KpiMoModel(String datetime, BigInteger moShortcode, BigInteger moReceive, 
            BigInteger moError, BigInteger moPending, BigInteger moFinished, 
            BigInteger moNotifyPending, BigInteger moNotifyFinished, BigInteger moRefund, 
            BigInteger moCharge) {
        this.datetime = datetime;
        this.moShortcode = moShortcode;
        this.moReceive = moReceive;
        this.moError = moError;
        this.moPending = moPending;
        this.moFinished = moFinished;
        this.moNotifyPending = moNotifyPending;
        this.moNotifyFinished = moNotifyFinished;
        this.moRefund = moRefund;
        this.moCharge = moCharge;
    }
    
    

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public BigInteger getMoShortcode() {
        return moShortcode;
    }

    public void setMoShortcode(BigInteger moShortcode) {
        this.moShortcode = moShortcode;
    }

    public BigInteger getMoReceive() {
        return moReceive;
    }

    public void setMoReceive(BigInteger moReceive) {
        this.moReceive = moReceive;
    }

    public BigInteger getMoError() {
        return moError;
    }

    public void setMoError(BigInteger moError) {
        this.moError = moError;
    }

    public BigInteger getMoPending() {
        return moPending;
    }

    public void setMoPending(BigInteger moPending) {
        this.moPending = moPending;
    }

    public BigInteger getMoFinished() {
        return moFinished;
    }

    public void setMoFinished(BigInteger moFinished) {
        this.moFinished = moFinished;
    }

    public BigInteger getMoNotifyPending() {
        return moNotifyPending;
    }

    public void setMoNotifyPending(BigInteger moNotifyPending) {
        this.moNotifyPending = moNotifyPending;
    }

    public BigInteger getMoNotifyFinished() {
        return moNotifyFinished;
    }

    public void setMoNotifyFinished(BigInteger moNotifyFinished) {
        this.moNotifyFinished = moNotifyFinished;
    }

    public BigInteger getMoRefund() {
        return moRefund;
    }

    public void setMoRefund(BigInteger moRefund) {
        this.moRefund = moRefund;
    }

    public BigInteger getMoCharge() {
        return moCharge;
    }

    public void setMoCharge(BigInteger moCharge) {
        this.moCharge = moCharge;
    }
}
