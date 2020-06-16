package com.wzm.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wzm
 * @description
 * @date 2020-05-27 20:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {

    private static final long serialVersionUID = -6974640020083706305L;

    private Long id;

    private String serial;
}
