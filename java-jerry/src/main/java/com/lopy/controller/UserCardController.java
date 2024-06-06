package com.lopy.controller;

import com.lopy.common.auth.AuthContext;
import com.lopy.common.constant.CommonConstant;
import com.lopy.common.dto.card.UserCardListDTO;
import com.lopy.common.form.card.UserCardDTO;
import com.lopy.common.vo.RespVO;
import com.lopy.common.vo.card.UserCardVO;
import com.lopy.service.biz.intf.UserCardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User Card API")
@RestController
@RequestMapping(CommonConstant.API.V1_PATH + "/user-card")
public class UserCardController {

    @Autowired
    private UserCardService userCardService;

    /**
     * Returns list of associated card for the current user
     * @param userCardListDTO: query params
     * @return User Card List
     */
    @PostMapping("/list")
    public RespVO<List<UserCardVO>> list(@RequestBody UserCardListDTO userCardListDTO) {
        userCardListDTO.setUserId(AuthContext.getUserId());
        List<UserCardVO> list = userCardService.listByQuery(userCardListDTO);
        return RespVO.ok(list);
    }

    /**
     * Create a user card and bind to the current user
     * @param userCardDTO: form for creation of card
     * @return Void
     */
    @PutMapping("/save")
    public RespVO<Void> save(@RequestBody UserCardDTO userCardDTO) {
        userCardDTO.setUserId(AuthContext.getUserId());
        userCardService.save(userCardDTO);
        return RespVO.ok();
    }

    /**
     * Delete the associated user card for the current user by given id
     * @param id: id of entity
     * @return Void
     */
    @PutMapping("/delete")
    public RespVO<Void> delete(@RequestParam Long id) {
        userCardService.delete(id);
        return RespVO.ok();
    }
}
