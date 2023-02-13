package repository;

import java.util.List;

public class Registers {

    public static List<String> values = List.of("$v0", "$v1");
    public static List<String> arguments = List.of("$a0", "$a1", "$a2", "$a3");
    public static List<String> temporaries = List.of("$t0", "$t1", "$t2", "$t3", "$t4", "$t5", "$t6", "$t7",  "$t8", "$t9");
    public static List<String> savedValues = List.of("$s0", "$s1", "$s2", "$s3", "$s4", "$s5", "$s6", "$s7");
}
