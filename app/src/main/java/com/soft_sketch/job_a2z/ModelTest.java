package com.soft_sketch.job_a2z;

public class ModelTest {
    private int testNo;
    private String ModeltestName;

    public ModelTest() {
    }

    public ModelTest(int testNo, String modeltestName) {
        this.testNo = testNo;
        ModeltestName = modeltestName;
    }

    public int getTestNo() {
        return testNo;
    }

    public String getModeltestName() {
        return ModeltestName;
    }
}
