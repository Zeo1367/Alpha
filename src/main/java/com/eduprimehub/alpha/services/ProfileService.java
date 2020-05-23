package com.eduprimehub.alpha.services;

import com.eduprimehub.alpha.models.entities.Club;
import com.eduprimehub.alpha.models.entities.Country;
import com.eduprimehub.alpha.models.entities.Sport;
import com.eduprimehub.alpha.models.objects.*;
import com.eduprimehub.alpha.repositories.ClubRepository;
import com.eduprimehub.alpha.repositories.CountryRepository;
import com.eduprimehub.alpha.repositories.SportRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    @Qualifier("sportRepository")
    @Autowired
    private SportRepository sportRepository;

    @Qualifier("countryRepository")
    @Autowired
    private CountryRepository countryRepository;

    @Qualifier("clubRepository")
    @Autowired
    private ClubRepository clubRepository;


    public UserInfo loadData(Integer sportId) throws BusinessException {
        List<Sport> sportList;
        List<Country> countryList;
        List<Club> clubList;

        Set<SportObject> sportObjects = null;
        Set<ClubObject> clubObjects = null;
        Set<CountryObject> countryObjects = null;

        if (sportId == null) {
            sportList = sportRepository.findByActive(Boolean.TRUE);
            sportObjects = sportList.stream().map(sport -> {
                SportObject sportObject = new SportObject();
                BeanUtils.copyProperties(sport, sportObject);
                return sportObject;
            }).collect(Collectors.toSet());


        } else {
            Sport sport = sportRepository.findByIdAndActive(sportId, Boolean.TRUE);
            clubList = clubRepository.findAllBySport(sport);
            countryList = countryRepository.findAllBySport(sport);

            clubObjects = clubList.stream().map(club -> {
                ClubObject clubObject = new ClubObject();
                clubObject.setId(club.getId());
                clubObject.setClubName(club.getClubName());
                return clubObject;
            }).collect(Collectors.toSet());

            countryObjects = countryList.stream().map(country -> {
                CountryObject clubObject = new CountryObject();
                clubObject.setId(country.getId());
                clubObject.setCountryName(country.getCountryName());
                return clubObject;
            }).collect(Collectors.toSet());
        }


        UserInfo userInfo = new UserInfo();
        userInfo.setSportObjects(sportObjects);
        userInfo.setClubObjects(clubObjects);
        userInfo.setCountryObjects(countryObjects);
        return userInfo;
    }
}
