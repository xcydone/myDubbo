package com.crossyf.dubbo.springtest.test.testExam;

import java.util.*;

public class voteCnt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()){
            // 候选人
            int num = scanner.nextInt();
            List<String> people = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                people.add(scanner.next());
            }

            // 票数
            int voteNum = scanner.nextInt();
            List<String> votes = new ArrayList<>();
            for (int i = 0; i < voteNum; i++) {
                votes.add(scanner.next());
            }

            // 打印
            Map<String, Integer> peToVoteMap = cntVote(people, votes);
            int cnt = 0;
            for(String key: peToVoteMap.keySet()){
                System.out.println((key + " : " + peToVoteMap.get(key)));
                cnt += peToVoteMap.get(key);
            }

            System.out.println("Invalid : " + (voteNum - cnt));
        }
    }

    public static Map<String, Integer> cntVote(List<String> people, List<String> votes) {
        Map<String, Integer> peToVoteMap = new LinkedHashMap<>();

        for (int i = 0; i < people.size(); i++) {
            peToVoteMap.put(people.get(i), 0);
            for (int j = 0; j < votes.size(); j++) {
                if(votes.get(j).equals(people.get(i))){
                    if(peToVoteMap.containsKey(people.get(i))){
                        peToVoteMap.put(people.get(i), peToVoteMap.get(people.get(i)) + 1);
                    }else{
                        peToVoteMap.put(people.get(i), 1);
                    }

                }
            }
        }
        return peToVoteMap;
    }
}
