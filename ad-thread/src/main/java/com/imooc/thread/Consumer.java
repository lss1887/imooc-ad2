package com.imooc.thread;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consumer  {
    private  String name;
    private  String area;
    private  boolean flag = false;
}
