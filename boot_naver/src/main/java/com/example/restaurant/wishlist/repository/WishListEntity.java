package com.example.restaurant.wishlist.repository;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WishListEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	
	private String title;
	
	private String category;
	
	private String address;
	
	private String roadAddress;
	
	private String homePageLink;
	
	private String ImageLink;
	
	private boolean isVisit;	
	private int visitCount;	
	private LocalDateTime lastVisitDate;
}
