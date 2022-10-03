package models.entity;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public class BaseEntity {
	
	@CreationTimestamp
	private LocalDateTime regDt;
	
	@UpdateTimestamp
	private LocalDateTime modDt;
}
