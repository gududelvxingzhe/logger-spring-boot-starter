package com.example.loggerspringbootstarter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.loggerspringbootstarter.config.LogProfiler;
import com.example.loggerspringbootstarter.domain.SysRole;
import com.example.loggerspringbootstarter.mapper.SysRoleMapper;
import com.example.loggerspringbootstarter.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
public class TestController {
	
	@Autowired
	private SysRoleService sysRoleService;
//	@Autowired
//	private SysRoleMapper sysRoleMapper;
	
	@LogProfiler("查询角色")
	@GetMapping(value = "/getRole")
	public Object getRole(){
//		SysRole sysRole = new SysRole();
//        sysRole.setCreatetime(new Date()).setRoledesc("管理员").setRoleName("admin").setUpdatetime(new Date());
//        sysRoleService.save(sysRole);
		QueryWrapper<SysRole> wrapper = new QueryWrapper<SysRole>().eq("role_name", "admin").eq("id",2);
		System.out.println(sysRoleService.list());
		
		return sysRoleService.list();
	}
	
	@LogProfiler("添加角色")
	@PostMapping("/addRole")
	public Object addRole(@RequestBody Map<String,Object> params){
		
		SysRole sysRole = new SysRole();
		sysRole.setRoleName((String) params.get("name"));
		sysRole.setRoledesc((String) params.get("roleDesc"));
		sysRole.setCreatetime(new Date());
		sysRole.setUpdatetime(new Date());
		sysRoleService.save(sysRole);
		return sysRole;
	}
	
	
	@LogProfiler("添加角色2")
	@PostMapping(value = "/addRole2", produces = { "application/json;charset=UTF-8" })
	public Object addRole2(@RequestBody SysRole sysRole){
		

		sysRoleService.save(sysRole);
		return sysRole;
	}
}
