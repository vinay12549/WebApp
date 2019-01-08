package com.medblaze.dbservice.impl;

import com.medblaze.beans.model.MetadataDto;
import com.medblaze.dbservice.MetadataService;
import com.medblaze.sqladapter.dao.MetadataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetadataServiceImpl implements MetadataService {

    private final MetadataDao metadataDao;

    @Autowired
    public MetadataServiceImpl(MetadataDao metadataDao) {
        this.metadataDao = metadataDao;
    }

    @Override
    public boolean deleteMetadata(String tenantId) {
        return metadataDao.deleteMetadata(tenantId);
    }

    @Override
    public MetadataDto updateMetadata(MetadataDto metadataDto) {
        return metadataDao.updateMetadata(metadataDto);
    }

    @Override
    public MetadataDto createMetadata(MetadataDto metadataDto) {
        return metadataDao.createMetadata(metadataDto);
    }

    @Override
    public MetadataDto getMetadata(String tenantId) {
        return metadataDao.getMetadata(tenantId);
    }
}
