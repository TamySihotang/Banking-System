package hello.converter;


import hello.util.ExtendedSpringBeanUtil;

import java.util.ArrayList;
import java.util.Collection;

/**
 * agus w on 3/2/16.
 */
public abstract class BaseVOConverter<Z, V, T> implements IBaseVOConverter<Z, V, T> {

    /**
     * transfer value from vo object to domain object
     * for enum value, please do manually using Enum.values()[ordinal]
     *
     * @param vo
     * @param model
     * @return
     */
    @Override
    public T transferVOToModel(Z vo, T model) {

        // Generated values should not be modified explicitly
        /*ExtendedSpringBeanUtil.copySpecificProperties(vo, model,
                new String[]{"id", "creationDate", "createdBy", "modificationDate", "modifiedBy", "version", "active"},
                new String[]{"id", "creationDate", "createdBy", "modificationDate", "modifiedBy", "version", "active"});*/
        ExtendedSpringBeanUtil.copySpecificProperties(vo, model,
                new String[]{"deleted"},
                new String[]{"deleted"});

        return model;
    }

    /**
     * transfer value from list of domain object to list of vo object
     *
     * @param models
     * @param vos
     * @return
     */
    @Override
    public Collection<V> transferListOfModelToListOfVO(Collection<T> models, Collection<V> vos) {
        if (null == vos) vos = new ArrayList<>();
        for (T model : models) {
            V vo = transferModelToVO(model, null);
            vos.add(vo);
        }
        return vos;
    }

    /**
     * transfer value from domain object to vo object
     *
     * @param model
     * @param vo
     * @return
     */
    @Override
    public V transferModelToVO(T model, V vo) {
        ExtendedSpringBeanUtil.copySpecificProperties(model,vo,
                new String[]{"secureId", "version"},
                new String[]{"id","version"});
        return vo;
    }
}
