/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.domainobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author johnson3yo
 */
@Entity
@Table(name = "call_log")
public class CallLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "source")
    private String source;
    @Column(name = "destination")
    private String destination;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;
    @Column(name = "no_of_participants")
    private Integer noOfParticipants;
    @Size(max = 256)
    @Column(name = "channels")
    private String channels;
    @Transient
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date day;
    @Column(name = "endpoint")
    private String endpoint;

    public CallLog() {
    }

    public CallLog(String source, String destination, Date startTime, Date endTime, Integer noOfParticipants, String channels, Date day, String endpoint) {
        this.source = source;
        this.destination = destination;
        this.startTime = startTime;
        this.endTime = endTime;
        this.noOfParticipants = noOfParticipants;
        this.channels = channels;
        this.day = day;
        this.endpoint = endpoint;
    }

    public CallLog(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getNoOfParticipants() {
        return noOfParticipants;
    }

    public void setNoOfParticipants(Integer noOfParticipants) {
        this.noOfParticipants = noOfParticipants;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CallLog)) {
            return false;
        }
        CallLog other = (CallLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.johnson3yo.ariproxy.datao.CallLog[ id=" + id + " ]";
    }

    public static class CallLogBuilder {

        private String source;
        private String destination;
        private Date startTime;
        private Date endTime;
        private Integer noOfParticipants;
        private String channels;
        private Date day;
        private String endpoint;

        public CallLogBuilder setSource(String source) {
            this.source = source;
            return this;
        }

        public CallLogBuilder setDestination(String destination) {
            this.destination = destination;
            return this;
        }

        public CallLogBuilder setStartTime(Date startTime) {
            this.startTime = startTime;
            return this;
        }

        public CallLogBuilder setEndTime(Date endTime) {
            this.endTime = endTime;
            return this;
        }

        public CallLogBuilder setNoOfParticipants(Integer noOfParticipants) {
            this.noOfParticipants = noOfParticipants;
            return this;
        }

        public CallLogBuilder setChannels(String channels) {
            this.channels = channels;
            return this;
        }

        public CallLogBuilder setDay(Date day) {
            this.day = day;
            return this;
        }

        public CallLogBuilder setEndpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public CallLog build() {
            return new CallLog(source, destination, startTime, endTime, noOfParticipants, channels, day, endpoint);
        }

    }

}
