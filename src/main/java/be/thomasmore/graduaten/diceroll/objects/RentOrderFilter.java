package be.thomasmore.graduaten.diceroll.objects;

import be.thomasmore.graduaten.diceroll.entity.RentOrder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class RentOrderFilter implements Specification<RentOrder> {

    // Filter Attributes
    private int orderId;
    private String userId;
    private String paid = "Disabled";

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

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
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
    public Predicate toPredicate(Root<RentOrder> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        // Define empty ArrayList to hold predicates
        List<Predicate> predicates = new ArrayList<Predicate>();

        // Add conditional predicates based on present search parameters
        if (orderId != 0) {
            predicates.add(criteriaBuilder.equal(root.get("rentOrderID"), orderId));
        }
        if (userId != null) {
            predicates.add(criteriaBuilder.equal(root.get("user").get("userId"), userId));
        }
        if (!paid.equals("Disabled")) {
            if (paid.equals("On")) {
                predicates.add(criteriaBuilder.equal(root.get("paid"), true));
            } else if (paid.equals("Off")) {
                predicates.add(criteriaBuilder.equal(root.get("paid"), false));
            }
        }

        // Convert ArrayList to predicate and return
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

    }
}
