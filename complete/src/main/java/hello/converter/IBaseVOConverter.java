package hello.converter;

import java.util.Collection;

public interface IBaseVOConverter<Z, V, T> {

    /**
     * transfer value from vo object to domain object
     * for enum value, please do manually using Enum.values()[ordinal]
     * @param vo
     * @param model
     * @return
     */
    public T transferVOToModel(Z vo, T model);

    /**
     * transfer value from list of domain object to list of vo object
     * @param models
     * @param vos
     * @return
     */
    public Collection<V> transferListOfModelToListOfVO(Collection<T> models, Collection<V> vos);

    /**
     * transfer value from domain object to vo object
     * @param model
     * @param vo
     * @return
     */
    public V transferModelToVO(T model, V vo);
}