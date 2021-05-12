package com.gfk.tdd.shuffler.shuffler;

import com.gfk.tdd.shuffler.entities.LanguageSkill;
import com.gfk.tdd.shuffler.entities.Pair;
import com.gfk.tdd.shuffler.entities.Participant;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class Shuffler {

    public List<Pair> ShuffleParticipants(List<Participant> participantList) {

        Map<String, List<Participant>> groupedParticipants = new HashMap<>();

        for (var participant: participantList) {
            for (var skill : participant.getSkills()) {
                if (!groupedParticipants.containsKey(skill.getLanguage())) {
                    groupedParticipants.put(skill.getLanguage(), new ArrayList<>());
                }
                groupedParticipants.get(skill.getLanguage()).add(participant);
            }
        }

        List<Pair> pairs = new ArrayList<>();

        for (var participant : participantList) {

            var languages = participant.getSkills().stream().map(LanguageSkill::getLanguage).collect(Collectors.toList());

            Participant joinedPartner = null;

            // Find Pair to join
            for(var pair : pairs) {
                String language = pair.getLanguage();
                if (languages.contains(language)) {
                    if (pair.getParticipants().size() == 1) {
                        var otherParticipant = pair.getParticipants().get(0);
                        int skillOfOtherParticipant = otherParticipant.getSkills().stream().filter(i -> i.getLanguage().equals(language)).map(LanguageSkill::getLevel).findFirst().get();
                        int mySkill = participant.getSkills().stream().filter(i -> i.getLanguage().equals(language)).map(LanguageSkill::getLevel).findFirst().get();
                        int joinedSkill = skillOfOtherParticipant + mySkill;

                        if (joinedSkill > 2 && joinedSkill < 6) {
                            pair.getParticipants().add(participant);
                            joinedPartner = otherParticipant;
                            break;
                        }
                    }
                }
            }

            // If participant joined a pair, remove him from all naively created pairs
            if (joinedPartner != null)
            {
                List<Pair> pairsToRemove = new ArrayList<>();
                for (var pair : pairs) {
                    if (pair.getParticipants().size() == 1 &&
                            (pair.getParticipants().get(0) == participant || pair.getParticipants().get(0).equals(joinedPartner))) {
                        pairsToRemove.add(pair);
                    }
                }
                pairs.removeAll(pairsToRemove);
                continue;
            }

            // Create naivly a new pair for each language combination
            for (String language : languages) {
                Pair newPair = new Pair();
                newPair.setParticipants(new ArrayList<>(Arrays.asList(participant)));
                newPair.setLanguage(language);
                pairs.add(newPair);
            }
        }

        // Collect and group all pairs with only one participant
        List<Pair> singlePairs = pairs.stream().filter(i -> i.getParticipants().size() == 1).toList();
        Map<String, List<Participant>> unpairedParticipantsByLanguage = new HashMap<>();
        for (Pair pair : singlePairs) {
            if (!unpairedParticipantsByLanguage.containsKey(pair.getLanguage())) {
                unpairedParticipantsByLanguage.put(pair.getLanguage(), new ArrayList<>());
            }
            unpairedParticipantsByLanguage.get(pair.getLanguage()).addAll(pair.getParticipants());
        }
        pairs.removeAll(singlePairs);

        List<Participant> pairedParticipants = new ArrayList<>();
        for (var kv : unpairedParticipantsByLanguage.entrySet()) {
            kv.getValue().removeAll(pairedParticipants);

            List<List<Participant>> partitions = new ArrayList<>();
            for (int i=0; i < kv.getValue().size(); i += 2) {
                partitions.add(kv.getValue().subList(i, Math.min(i + 2, kv.getValue().size())));
            }

            for (var partition : partitions) {
                Pair newPair = new Pair();
                newPair.setLanguage(kv.getKey());
                newPair.setParticipants(partition);
                pairedParticipants.addAll(partition);
                pairs.add(newPair);
            }
        }

        return pairs;
    }

}
