package com.crossyf.dubbo.springbatch.step.testListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Line implements Serializable {

    private String name;
    private LocalDate dob;
    private Long age;
}

