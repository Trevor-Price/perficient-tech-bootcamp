package com.perficient.techbootcamp.ecommerce.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
@Table(schema = "ecommerce")
public class Brand implements Serializable{
	
	private static final long serialVersionUID = 5447891919722293324L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long brandId;
	
	private @NonNull String name;
	private String logo;
	
	@OneToMany(mappedBy="brand")
	private Set<Product> products;
	
}
