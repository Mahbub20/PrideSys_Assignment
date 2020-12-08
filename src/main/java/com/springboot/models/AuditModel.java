package com.springboot.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)

public abstract class AuditModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	    @Column(name = "start_date", nullable = false, updatable = false)
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date startDate;

	    @Column(name = "end_date", nullable = false)
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date endDate;

		public Date getStartDate() {
			return startDate;
		}

		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}

	    
	}
