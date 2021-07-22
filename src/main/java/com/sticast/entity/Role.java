package com.sticast.entity;

import javax.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
}