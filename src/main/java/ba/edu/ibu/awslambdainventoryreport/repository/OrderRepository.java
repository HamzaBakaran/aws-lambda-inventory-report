package ba.edu.ibu.awslambdainventoryreport.repository;



import ba.edu.ibu.awslambdainventoryreport.dto.OrderCustomDTO;
import ba.edu.ibu.awslambdainventoryreport.model.Order;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {

    @Aggregation({
            "{$match: {orderDate: { $gte: ?#{T(java.time.LocalDate).now().atStartOfDay()}, $lt: ?#{T(java.time.LocalDate).now().plusDays(1).atStartOfDay()} } }}", // Filter orders made today
            "{$unwind: '$productIds'}",
            "{$lookup: {from: 'product', let: {productId: '$productIds.product_id'}, " +
                    "pipeline: [{$match: {$expr: {$eq: ['$_id', {$toObjectId: '$$productId'}]}}}], " +
                    "as: 'productInfo'}}",
            "{$unwind: '$productInfo'}",
            "{$group: {_id: '$_id', orderDate: {$first: '$orderDate'}, " +
                    "products: {$push: {productName: '$productInfo.name', quantity: '$productIds.quantity', " +
                    "price: '$productInfo.sellingPrice', subtotal: {$multiply: ['$productIds.quantity', '$productInfo.sellingPrice']}}}}}",
            "{$sort: {orderDate: -1}}",
            "{$project: {_id: 0, id: '$_id', orderDate: 1, products: 1, " +
                    "total: {$sum: '$products.subtotal'}}}"
    })
    List<OrderCustomDTO> findAllCustom();





}