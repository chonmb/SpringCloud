package com.springboot.cloud.gateway.admin.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/21 9:50
 */
@Entity
@Table(name = "gateway_route")
@Getter
@Setter
public class GatewayRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String uri;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String predicates;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String filters;

    @Column(nullable = false)
    private Integer ordered;
}
