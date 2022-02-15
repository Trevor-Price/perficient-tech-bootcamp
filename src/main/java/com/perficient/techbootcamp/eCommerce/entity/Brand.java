package com.perficient.techbootcamp.ecommerce.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@Table(schema = "ecommerce")
public class Brand implements Serializable{
	
	private static final long serialVersionUID = 5447891919722293324L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		private @Getter @Setter Long brandId;
	
	private @NonNull @Getter @Setter String name;
	private @Getter @Setter String logo;
	
	@OneToMany(mappedBy="brand")
	private Set<Product> products;
	
}
