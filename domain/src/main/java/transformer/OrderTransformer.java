package transformer;

import jakarta.enterprise.context.ApplicationScoped;
import models.Customer;
import models.Item;
import models.Order;

import java.text.DecimalFormat;
import java.util.stream.Collectors;

@ApplicationScoped
public class OrderTransformer {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public String toXml(Order order) {
        double total = order.getItems().stream()
                .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
                .sum();

        String products = order.getItems().stream()
                .map(this::toProductXml)
                .collect(Collectors.joining("\n"));

        Customer c = order.getCustomer();

        return String.format("""
                        <PurchaseOrder>
                          <Reference>%s</Reference>
                          <ClientInfo id=\"%s\">
                            <Name>%s</Name>
                            <Contact>%s</Contact>
                          </ClientInfo>
                          <Products>
                            %s
                          </Products>
                          <OrderDateTime>%s</OrderDateTime>
                          <TotalOrderAmount>%s</TotalOrderAmount>
                        </PurchaseOrder>""",
                order.getOrderId(), c.getId(), c.getName(), c.getEmail(), products,
                order.getOrderDate(), df.format(total));
    }

    private String toProductXml(Item item) {
        double total = item.getQuantity() * item.getUnitPrice();
        return String.format("""
                        <Product code=\"%s\">
                          <Quantity>%d</Quantity>
                          <Price>%s</Price>
                          <TotalAmount>%s</TotalAmount>
                        </Product>""",
                item.getProductId(), item.getQuantity(), df.format(item.getUnitPrice()), df.format(total));
    }
}
