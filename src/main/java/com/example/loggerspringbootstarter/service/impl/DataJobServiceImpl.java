package com.example.loggerspringbootstarter.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.loggerspringbootstarter.domain.DataJob;
import com.example.loggerspringbootstarter.mapper.DataJobMapper;
import com.example.loggerspringbootstarter.service.DataJobService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class DataJobServiceImpl extends ServiceImpl<DataJobMapper, DataJob>
    implements DataJobService {

}




