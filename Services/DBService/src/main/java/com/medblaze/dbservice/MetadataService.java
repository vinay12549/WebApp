package com.medblaze.dbservice;


import com.medblaze.beans.model.MetadataDto;

public interface MetadataService {

    MetadataDto createMetadata(MetadataDto metadataDto);
    MetadataDto updateMetadata(MetadataDto metadataDto);
    MetadataDto getMetadata(String tenantId);
    boolean deleteMetadata(String tenantId);

}
