package pers.lionlinzq.excel.utils;

import org.springframework.stereotype.Component;

/**
 * snowflake的结构如下(每部分用-分开):
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 * 第一位为未使用，接下来的41位为毫秒级时间(41位的长度可以使用69年)，然后是5位datacenterId和5位workerId(10位的长度最多支持部署1024个节点） ，最后12位是毫秒内的计数（12位的计数顺序号支持每个节点每毫秒产生4096个ID序号）
 * 一共加起来刚好64位，为一个Long型。(转换成字符串长度为18)
 * snowflake生成的ID整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞（由datacenter和workerId作区分），并且效率较高。据说：snowflake每秒能够产生26万个ID。
 */
@Component
public class IdWorker {

    private final long twepoch = 1288834974657L;
    private final long workerIdBits = 5L;
    private final long datacenterIdBits = 5L;
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private final long sequenceBits = 12L;
    private final long workerIdShift = sequenceBits;
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    private final long timestampLeftShift = sequenceBits + workerIdBits
            + datacenterIdBits;
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    private static IdWorker idWorker;

    public long getWorkerId() {
        return workerId;
    }

    public long getDatacenterId() {
        return datacenterId;
    }

    private long workerId;

    private long datacenterId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    private void getWorkIdCfg() {
        workerId = Long.valueOf(System.getProperty("id.workerid", "3"));
        datacenterId = Long.valueOf(System.getProperty("id.datacenterid", "3"));
    }

    public IdWorker() {
        getWorkIdCfg();
    }

    public IdWorker(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format(
                    "worker Id can't be greater than %d or less than 0",
                    maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format(
                    "datacenter Id can't be greater than %d or less than 0",
                    maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format(
                            "Clock moved backwards.  Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift) | sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

//    /**
//     * 生成id
//     *
//     * @return
//     */
//    public long genId() {
//        if (idWorker == null) {
//            idWorker = new IdWorker(workerId, datacenterId);
//        }
//        return idWorker.nextId();
//    }

    public static void main(String[] args) {
//		IdWorker idWorker = new IdWorker(0, 0);
//		long l1 = System.currentTimeMillis();
//		for (int i = 0; i < 1000000; i++) {
//			long id = idWorker.nextId();
//			//System.out.println(id);
//		}
//		long l2 = System.currentTimeMillis();
//		System.out.println("耗时"+(l2-l1)/1000 +"s");

        System.out.println(-1L << 5);
        System.out.println(-1L ^ -32);
        System.out.println(Long.toBinaryString(-32));
        System.out.println(Long.toBinaryString(32));
        System.out.println(Long.toBinaryString(-1));
        System.out.println(Long.toBinaryString(1));
        System.out.println(Long.toBinaryString(-2));
        //-1L ^ (-1L << 5)
    }

    public static void setIdWorker(IdWorker idWorker) {
        System.out.println("idWorker : " + idWorker);
        IdWorker.idWorker = idWorker;
    }

    public void setDatacenterId(long datacenterId) {
        this.datacenterId = datacenterId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }
}
