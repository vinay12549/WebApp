package com.medblaze.sqladapter.dao;


import com.medblaze.beans.model.MetadataDto;

public interface MetadataDao {

    MetadataDto createMetadata(MetadataDto metadataDto);
    MetadataDto updateMetadata(MetadataDto metadataDto);
    MetadataDto getMetadata(String tenantId);
    boolean deleteMetadata(String tenantId);

}
