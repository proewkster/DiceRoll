package be.thomasmore.graduaten.diceroll.objects;

import be.thomasmore.graduaten.diceroll.entity.SaleOrder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class SaleOrderFilter implements Specification<SaleOrder> {

    // Filter Attributes
    private int orderId;
    private String userId;
    private Date startDate = new Date(new GregorianCalendar(2020, 01, 01).getTimeInMillis());
    private Date endDate = new Date(GregorianCalendar.getInstance().getTimeInMillis());
    private String paid = "Disabled";
    private String delivered = "Disabled";

    // Pagination Attributes
    private int currentPage = 0;
    private int ObjectsPerPage = 10;
    private int totalPages = 0;

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        if (userId.equals("")) {
            this.userId = null;
        }
        else {
            this.userId = userId;
        }
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getDelivered() {
        return delivered;
    }

    public void setDelivered(String delivered) {
        this.delivered = delivered;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getObjectsPerPage() {
        return ObjectsPerPage;
    }

    public void setObjectsPerPage(int objectsPerPage) {
        ObjectsPerPage = objectsPerPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

// Methods

    @Override
    public Predicate toPredicate(Root<SaleOrder> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        // Define empty ArrayList to hold predicates
        List<Predicate> predicates = new ArrayList<Predicate>();

        // Add conditional predicates based on present search parameters
        if (orderId != 0) {
            predicates.add(criteriaBuilder.equal(root.get("saleOrderID"), orderId));
        }
        if (userId != null) {
            predicates.add(criteriaBuilder.equal(root.get("user").get("userId"), userId));
        }
        if (!paid.equals("Disabled")) {
            if (paid.equals("On")) {
                predicates.add(criteriaBuilder.equal(root.get("paid"), true));
            }
            else if (paid.equals("Off")) {
                predicates.add(criteriaBuilder.equal(root.get("paid"), false));
            }
        }
        if (!delivered.equals("Disabled")) {
            if (delivered.equals("On")) {
                predicates.add(criteriaBuilder.equal(root.get("delivered"), true));
            }
            else if (delivered.equals("Off")) {
                predicates.add(criteriaBuilder.equal(root.get("delivered"), false));
            }
        }
        predicates.add(criteriaBuilder.between(root.get("orderDate"), startDate, endDate));

        // Convert ArrayList to predicate and return
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

    }
}
