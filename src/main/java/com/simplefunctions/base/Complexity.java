package com.simplefunctions.base;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 14:25
 */
public class Complexity {
    private final long cpu;
    private final long memory;

    public Complexity(long cpu, long memory) {
        if (cpu < 0) {
            throw new IllegalArgumentException("cpu<0");
        }
        if (memory < 0) {
            throw new IllegalArgumentException("memory<0");
        }
        this.cpu = cpu;
        this.memory = memory;
    }

    public long getCpu() {
        return cpu;
    }

    public long getMemory() {
        return memory;
    }

    private static void assertNoOverflow(long v1, long v2) throws ComplexityOverflowException {
        boolean overflow = (~(v1 ^ v2) & (v1 ^ (v1 + v2))) < 0;
        if (overflow) {
            throw new ComplexityOverflowException();
        }
    }

    public Complexity combine(Complexity c) throws ComplexityOverflowException {
        assertNoOverflow(getCpu(), c.getCpu());
        assertNoOverflow(getMemory(), c.getMemory());
        return new Complexity(getCpu() + c.getCpu(), getMemory() + c.getMemory());
    }

    public static Complexity combine(Iterable<Complexity> complexities)
            throws ComplexityOverflowException {
        int cpuCur = 0;
        int memCur = 0;
        for (final Complexity complexity : complexities) {
            assertNoOverflow(cpuCur, complexity.getCpu());
            cpuCur += complexity.getCpu();
            assertNoOverflow(memCur, complexity.getMemory());
            memCur += complexity.getMemory();
        }
        return new Complexity(cpuCur, memCur);
    }

    public Complexity multiply(int by) {
        long cpu = this.getCpu() * by;
        long mem = this.getMemory() * by;
        return new Complexity(cpu, memory);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Complexity that = (Complexity) o;

        if (cpu != that.cpu) return false;
        if (memory != that.memory) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (cpu ^ (cpu >>> 32));
        result = 31 * result + (int) (memory ^ (memory >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Complexity{" +
                "cpu=" + cpu +
                ", memory=" + memory +
                '}';
    }
}
