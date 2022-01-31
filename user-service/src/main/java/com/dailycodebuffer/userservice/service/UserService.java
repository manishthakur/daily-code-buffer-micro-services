package com.dailycodebuffer.userservice.service;

import com.dailycodebuffer.userservice.VO.Department;
import com.dailycodebuffer.userservice.VO.ResponseTemplateVO;
import com.dailycodebuffer.userservice.entity.User;
import com.dailycodebuffer.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {

        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        User user = userRepository.findUserById(userId);

        Department department =
                restTemplate.getForObject("http://localhost:9001/department/" + user.getDepartmentId(),
                        Department.class);

        responseTemplateVO.setUser(user);
        responseTemplateVO.setDepartment(department);

        return responseTemplateVO;
    }
}
