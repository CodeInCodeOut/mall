package com.dayuanit.shop.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayuanit.shop.exception.ShopException;
import com.dayuanit.shop.mapper.ProvinceAndCityMapper;
import com.dayuanit.shop.service.ProvinceAndCityService;
import com.dayuanit.shop.service.RedisService;

@Service
public class ProvinceAndCityServiceImpl implements ProvinceAndCityService {

	private static final  Logger log = LoggerFactory.getLogger(ProvinceAndCityServiceImpl.class);
	
	@Autowired
	private ProvinceAndCityMapper provinceAndCityMapper;
	
	@Resource(name="redisTempServiceImpl")
	private RedisService redisService;
	
	@Override
	public List<Map<String, String>> listProvince() {
		// TODO Auto-generated method stub
		String provinceKey = "mall:province";
		boolean flag = redisService.hasKey(provinceKey);
		if (flag) {
			log.info(">>>>>省份走缓存");
			return redisService.getPCA(provinceKey);
		}
		
		List<Map<String, String>> listProvince = provinceAndCityMapper.listProvince();
		
		if (null == listProvince) {
			throw new ShopException("新增地址 查询省代码失败 ");
		}
		
		redisService.setListPCA(provinceKey, listProvince);
		
		return listProvince;
	}

	@Override
	public List<Map<String, String>> listCity(String provincecode) {
		// TODO Auto-generated method stub
		String cityKey = String.format("mall:city:%s", provincecode);
		boolean flag = redisService.hasKey(cityKey);
		if (flag) {
			log.info(">>>>>city走缓存");
			return redisService.getPCA(cityKey);
		}
		
		List<Map<String, String>> listCity = provinceAndCityMapper.listCity(provincecode);
		if (null == listCity) {
			throw new ShopException("新增地址 查询市区代码失败 ");
		}
		
		redisService.setListPCA(cityKey, listCity);
		
		return listCity;
	}

	@Override
	public List<Map<String, String>> listArea(String citycode) {
		
		String areaKey = String.format("mall:area:%s", citycode);
		boolean flag = redisService.hasKey(areaKey);
		if (flag) {
			log.info(">>>>>area走缓存");
			return redisService.getPCA(areaKey);
		}
		
		List<Map<String, String>> listArea = provinceAndCityMapper.listArea(citycode);
		
		if (null == listArea) {
			throw new ShopException("新增地址 查询区域代码失败 ");
		}
		
		redisService.setListPCA(areaKey, listArea);
		
		return listArea;
	}

	@Override
	public String getProvince(String code) {
		// TODO Auto-generated method stub
		String provincename = provinceAndCityMapper.getProvince(code);
		if (null == provincename) {
			throw new ShopException("新增地址 查询省名字失败 ");
		}
		return provincename;
	}

	@Override
	public String getCity(String code) {
		// TODO Auto-generated method stub
		
		String cityname = provinceAndCityMapper.getCity(code);
		
		if (null == cityname) {
			throw new ShopException("新增地址 查询市名字失败 ");
		}
		return cityname;
	}

	@Override
	public String getArea(String code) {
		// TODO Auto-generated method stub
		String areaname = provinceAndCityMapper.getArea(code);
		
		if (null == areaname) {
			throw new ShopException("新增地址 查询区名字失败 ");
		}
		return areaname;
	}

}
