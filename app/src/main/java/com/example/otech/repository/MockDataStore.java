package com.example.otech.repository;

import com.example.otech.model.Address;
import com.example.otech.model.CartItem;
import com.example.otech.model.Order;
import com.example.otech.model.Product;
import com.example.otech.model.Review;
import com.example.otech.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class MockDataStore {
    private static MockDataStore instance;
    
    private ArrayList<Product> products;
    private ArrayList<User> users;
    private ArrayList<Order> orders;
    private HashMap<String, ArrayList<CartItem>> userCarts; // userId -> CartItems
    private HashMap<String, ArrayList<Product>> userWishlists; // userId -> Products
    private HashMap<String, ArrayList<Address>> userAddresses; // userId -> Addresses
    private HashMap<String, ArrayList<Review>> productReviews; // productId -> Reviews
    
    private MockDataStore() {
        products = new ArrayList<>();
        users = new ArrayList<>();
        orders = new ArrayList<>();
        userCarts = new HashMap<>();
        userWishlists = new HashMap<>();
        userAddresses = new HashMap<>();
        productReviews = new HashMap<>();
        
        initUsers();
        initProducts();
    }
    
    public static synchronized MockDataStore getInstance() {
        if (instance == null) {
            instance = new MockDataStore();
        }
        return instance;
    }
    
    private void initUsers() {
        users.add(new User("1", "admin", "admin", "Admin User", 
                "0123456789", "123 Admin St", "admin@otech.com", "admin"));
        users.add(new User("2", "user", "user", "Test User", 
                "0987654321", "456 User Ave", "user@otech.com", "user"));
    }
    
    private void initProducts() {
        // Gaming Laptops
        Product p1 = new Product("1", "ASUS ROG Strix G16", 35990000, 42990000,
                "Laptop gaming hiệu năng cao với chip Intel Core i7 Gen 13",
                "https://via.placeholder.com/300x200?text=ASUS+ROG+Strix",
                "ASUS", "Gaming",
                "CPU: Intel Core i7-13650HX, RAM: 16GB DDR5, GPU: RTX 4060 8GB, SSD: 512GB NVMe, Display: 16\" FHD 165Hz",
                4.5f, 50);
        p1.setSoldCount(234);
        p1.setImageUrls(getLaptopImages());
        products.add(p1);
        
        Product p2 = new Product("2", "MSI Katana 15", 28990000, 34990000,
                "Laptop gaming MSI với thiết kế hầm hố",
                "https://via.placeholder.com/300x200?text=MSI+Katana",
                "MSI", "Gaming",
                "CPU: Intel Core i7-12650H, RAM: 16GB DDR4, GPU: RTX 4050 6GB, SSD: 512GB NVMe, Display: 15.6\" FHD 144Hz",
                4.3f, 30);
        p2.setSoldCount(189);
        p2.setImageUrls(getLaptopImages());
        products.add(p2);
        
        Product p3 = new Product("3", "Acer Predator Helios 300", 32990000, 38990000,
                "Laptop gaming Acer Predator với hệ thống tản nhiệt tốt",
                "https://via.placeholder.com/300x200?text=Acer+Predator",
                "Acer", "Gaming",
                "CPU: Intel Core i7-12700H, RAM: 16GB DDR5, GPU: RTX 4060 8GB, SSD: 1TB NVMe, Display: 15.6\" FHD 165Hz",
                4.4f, 25);
        p3.setSoldCount(312);
        p3.setImageUrls(getLaptopImages());
        products.add(p3);
        
        // Workstation Laptops
        Product p4 = new Product("4", "Dell Precision 5570", 45990000, 52990000,
                "Workstation chuyên nghiệp cho công việc đồ họa 3D",
                "https://via.placeholder.com/300x200?text=Dell+Precision",
                "Dell", "Workstation",
                "CPU: Intel Core i7-12800H, RAM: 32GB DDR5, GPU: NVIDIA RTX A2000 8GB, SSD: 1TB NVMe, Display: 15.6\" 4K OLED",
                4.6f, 15);
        p4.setSoldCount(87);
        p4.setImageUrls(getLaptopImages());
        products.add(p4);
        
        Product p5 = new Product("5", "HP ZBook Studio G9", 48990000, 55990000,
                "Laptop Workstation mỏng nhẹ từ HP",
                "https://via.placeholder.com/300x200?text=HP+ZBook",
                "HP", "Workstation",
                "CPU: Intel Core i9-12900H, RAM: 32GB DDR5, GPU: NVIDIA RTX A3000 12GB, SSD: 1TB NVMe, Display: 15.6\" 4K DreamColor",
                4.7f, 10);
        p5.setSoldCount(65);
        p5.setImageUrls(getLaptopImages());
        products.add(p5);
        
        // Ultrabook - Văn phòng
        Product p6 = new Product("6", "MacBook Air M2", 28990000, 32990000,
                "Laptop mỏng nhẹ với chip Apple M2 tiết kiệm pin",
                "https://via.placeholder.com/300x200?text=MacBook+Air+M2",
                "Apple", "Ultrabook",
                "CPU: Apple M2 8-core, RAM: 8GB Unified, GPU: 10-core, SSD: 256GB, Display: 13.6\" Liquid Retina",
                4.8f, 40);
        p6.setSoldCount(478);
        p6.setImageUrls(getLaptopImages());
        products.add(p6);
        
        Product p7 = new Product("7", "Dell XPS 13 Plus", 32990000, 38990000,
                "Ultrabook cao cấp với thiết kế hiện đại",
                "https://via.placeholder.com/300x200?text=Dell+XPS+13",
                "Dell", "Ultrabook",
                "CPU: Intel Core i7-1360P, RAM: 16GB LPDDR5, GPU: Intel Iris Xe, SSD: 512GB NVMe, Display: 13.4\" FHD+",
                4.6f, 35);
        p7.setSoldCount(291);
        p7.setImageUrls(getLaptopImages());
        products.add(p7);
        
        Product p8 = new Product("8", "Lenovo ThinkPad X1 Carbon Gen 11", 36990000, 42990000,
                "Laptop doanh nhân bền bỉ, bảo mật cao",
                "https://via.placeholder.com/300x200?text=ThinkPad+X1",
                "Lenovo", "Ultrabook",
                "CPU: Intel Core i7-1355U, RAM: 16GB LPDDR5, GPU: Intel Iris Xe, SSD: 512GB NVMe, Display: 14\" WUXGA",
                4.7f, 20);
        p8.setSoldCount(156);
        p8.setImageUrls(getLaptopImages());
        products.add(p8);
        
        // Văn phòng giá rẻ
        Product p9 = new Product("9", "Asus Vivobook 15", 12990000, 15990000,
                "Laptop văn phòng giá rẻ, phù hợp học sinh sinh viên",
                "https://via.placeholder.com/300x200?text=Asus+Vivobook",
                "ASUS", "Văn phòng",
                "CPU: Intel Core i5-1235U, RAM: 8GB DDR4, GPU: Intel UHD, SSD: 512GB NVMe, Display: 15.6\" FHD",
                4.2f, 60);
        p9.setSoldCount(523);
        p9.setImageUrls(getLaptopImages());
        products.add(p9);
        
        Product p10 = new Product("10", "HP Pavilion 14", 14990000, 17990000,
                "Laptop văn phòng nhỏ gọn, thiết kế đẹp mắt",
                "https://via.placeholder.com/300x200?text=HP+Pavilion",
                "HP", "Văn phòng",
                "CPU: Intel Core i5-1335U, RAM: 8GB DDR4, GPU: Intel Iris Xe, SSD: 256GB NVMe, Display: 14\" FHD",
                4.3f, 45);
        p10.setSoldCount(401);
        p10.setImageUrls(getLaptopImages());
        products.add(p10);
    }
    
    private ArrayList<String> getLaptopImages() {
        ArrayList<String> images = new ArrayList<>();
        images.add("laptop1");
        images.add("laptop2");
        images.add("laptop3");
        images.add("laptop4");
        images.add("laptop5");
        return images;
    }
    
    // ==================== PRODUCT METHODS ====================
    public ArrayList<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
    
    public Product getProductById(String productId) {
        for (Product p : products) {
            if (p.getId().equals(productId)) {
                return p;
            }
        }
        return null;
    }
    
    public ArrayList<Product> getProductsByCategory(String category) {
        ArrayList<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return result;
    }
    
    public ArrayList<Product> searchProducts(String query) {
        ArrayList<Product> result = new ArrayList<>();
        String lowerQuery = query.toLowerCase();
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(lowerQuery) ||
                p.getBrand().toLowerCase().contains(lowerQuery) ||
                p.getCategory().toLowerCase().contains(lowerQuery)) {
                result.add(p);
            }
        }
        return result;
    }
    
    public ArrayList<Product> getRelatedProducts(String productId, int limit) {
        ArrayList<Product> result = new ArrayList<>();
        Product currentProduct = getProductById(productId);
        
        if (currentProduct == null) {
            return result;
        }
        
        // Get products from same category or brand
        for (Product p : products) {
            if (!p.getId().equals(productId)) {
                if (p.getCategory().equals(currentProduct.getCategory()) ||
                    p.getBrand().equals(currentProduct.getBrand())) {
                    result.add(p);
                }
            }
            
            if (result.size() >= limit) {
                break;
            }
        }
        
        // If not enough, add random products
        if (result.size() < limit) {
            for (Product p : products) {
                if (!p.getId().equals(productId) && !result.contains(p)) {
                    result.add(p);
                    if (result.size() >= limit) {
                        break;
                    }
                }
            }
        }
        
        return result;
    }
    
    public boolean addProduct(Product product) {
        if (product.getId() == null || product.getId().isEmpty()) {
            product.setId(UUID.randomUUID().toString());
        }
        return products.add(product);
    }
    
    public boolean updateProduct(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(product.getId())) {
                products.set(i, product);
                return true;
            }
        }
        return false;
    }
    
    public boolean deleteProduct(String productId) {
        return products.removeIf(p -> p.getId().equals(productId));
    }
    
    // ==================== USER METHODS ====================
    public User login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
    
    public boolean register(User user) {
        // Check if username exists
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                return false;
            }
        }
        if (user.getId() == null || user.getId().isEmpty()) {
            user.setId(UUID.randomUUID().toString());
        }
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("user");
        }
        return users.add(user);
    }
    
    public boolean resetPassword(String username, String newPassword) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                u.setPassword(newPassword);
                return true;
            }
        }
        return false;
    }
    
    public User getUserByUsername(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }
    
    // ==================== CART METHODS ====================
    public ArrayList<CartItem> getCart(String userId) {
        if (!userCarts.containsKey(userId)) {
            userCarts.put(userId, new ArrayList<>());
        }
        return userCarts.get(userId);
    }
    
    public boolean addToCart(String userId, Product product, int quantity) {
        ArrayList<CartItem> cart = getCart(userId);
        
        // Check if product already in cart
        for (CartItem item : cart) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return true;
            }
        }
        
        // Add new item
        CartItem newItem = new CartItem(UUID.randomUUID().toString(), userId, product, quantity);
        return cart.add(newItem);
    }
    
    public boolean updateCartItemQuantity(String userId, String cartItemId, int quantity) {
        ArrayList<CartItem> cart = getCart(userId);
        for (CartItem item : cart) {
            if (item.getId().equals(cartItemId)) {
                if (quantity <= 0) {
                    return cart.remove(item);
                }
                item.setQuantity(quantity);
                return true;
            }
        }
        return false;
    }
    
    public boolean removeFromCart(String userId, String cartItemId) {
        ArrayList<CartItem> cart = getCart(userId);
        return cart.removeIf(item -> item.getId().equals(cartItemId));
    }
    
    public void clearCart(String userId) {
        userCarts.put(userId, new ArrayList<>());
    }
    
    public double getCartTotal(String userId) {
        ArrayList<CartItem> cart = getCart(userId);
        double total = 0;
        for (CartItem item : cart) {
            total += item.getTotalPrice();
        }
        return total;
    }
    
    // ==================== WISHLIST METHODS ====================
    public ArrayList<Product> getWishlist(String userId) {
        if (!userWishlists.containsKey(userId)) {
            userWishlists.put(userId, new ArrayList<>());
        }
        return userWishlists.get(userId);
    }
    
    public boolean addToWishlist(String userId, Product product) {
        ArrayList<Product> wishlist = getWishlist(userId);
        
        // Check if already in wishlist
        for (Product p : wishlist) {
            if (p.getId().equals(product.getId())) {
                return false;
            }
        }
        
        product.setFavorite(true);
        return wishlist.add(product);
    }
    
    public boolean removeFromWishlist(String userId, String productId) {
        ArrayList<Product> wishlist = getWishlist(userId);
        boolean removed = wishlist.removeIf(p -> p.getId().equals(productId));
        
        if (removed) {
            Product product = getProductById(productId);
            if (product != null) {
                product.setFavorite(false);
            }
        }
        
        return removed;
    }
    
    public boolean isInWishlist(String userId, String productId) {
        ArrayList<Product> wishlist = getWishlist(userId);
        for (Product p : wishlist) {
            if (p.getId().equals(productId)) {
                return true;
            }
        }
        return false;
    }
    
    // ==================== ORDER METHODS ====================
    public Order checkout(String userId, String deliveryAddress, String phone) {
        ArrayList<CartItem> cart = getCart(userId);
        if (cart.isEmpty()) {
            return null;
        }
        
        double total = getCartTotal(userId);
        Order order = new Order(UUID.randomUUID().toString(), userId, 
                new ArrayList<>(cart), total, deliveryAddress, phone);
        orders.add(order);
        clearCart(userId);
        
        return order;
    }
    
    public Order checkoutSelectedItems(String userId, ArrayList<CartItem> selectedItems, 
                                       String deliveryAddress, String phone) {
        if (selectedItems == null || selectedItems.isEmpty()) {
            return null;
        }
        
        // Calculate total from selected items
        double total = 0;
        for (CartItem item : selectedItems) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        
        // Create order
        Order order = new Order(UUID.randomUUID().toString(), userId, 
                new ArrayList<>(selectedItems), total, deliveryAddress, phone);
        orders.add(order);
        
        // Update soldCount for each product
        for (CartItem item : selectedItems) {
            Product product = getProductById(item.getProduct().getId());
            if (product != null) {
                product.setSoldCount(product.getSoldCount() + item.getQuantity());
            }
        }
        
        // Remove selected items from cart
        ArrayList<CartItem> cart = getCart(userId);
        for (CartItem selectedItem : selectedItems) {
            cart.removeIf(item -> item.getProduct().getId().equals(selectedItem.getProduct().getId()));
        }
        
        return order;
    }
    
    public ArrayList<Order> getUserOrders(String userId) {
        ArrayList<Order> userOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUserId().equals(userId)) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }
    
    public ArrayList<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }
    
    public Order getOrderById(String orderId) {
        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }
    
    public boolean updateOrderStatus(String orderId, String status) {
        Order order = getOrderById(orderId);
        if (order != null) {
            order.setStatus(status);
            return true;
        }
        return false;
    }
    
    public boolean cancelOrder(String orderId, String reason) {
        Order order = getOrderById(orderId);
        if (order != null && order.canBeCancelled()) {
            order.setStatus("cancelled");
            order.setCancelReason(reason);
            return true;
        }
        return false;
    }
    
    // Admin helper
    public User getUserById(String userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
    
    public ArrayList<Order> getOrdersByUserId(String userId) {
        ArrayList<Order> userOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUserId().equals(userId)) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }
    
    // ==================== ADDRESS MANAGEMENT ====================
    
    public ArrayList<Address> getUserAddresses(String userId) {
        if (!userAddresses.containsKey(userId)) {
            userAddresses.put(userId, new ArrayList<>());
        }
        return userAddresses.get(userId);
    }
    
    public Address addAddress(String userId, String recipientName, String phone, 
                             String city, String district, String ward, String addressLine, 
                             boolean isDefault) {
        ArrayList<Address> addresses = getUserAddresses(userId);
        
        // If setting as default, unset all other defaults
        if (isDefault) {
            for (Address addr : addresses) {
                addr.setDefault(false);
            }
        }
        
        // Create full address string
        String fullAddress = addressLine + ", " + ward + ", " + district + ", " + city;
        
        Address newAddress = new Address(
            UUID.randomUUID().toString(),
            userId,
            recipientName,
            phone,
            fullAddress,
            isDefault
        );
        
        addresses.add(newAddress);
        return newAddress;
    }
    
    public boolean updateAddress(Address address) {
        ArrayList<Address> addresses = getUserAddresses(address.getUserId());
        for (int i = 0; i < addresses.size(); i++) {
            if (addresses.get(i).getId().equals(address.getId())) {
                // If setting as default, unset all other defaults
                if (address.isDefault()) {
                    for (Address addr : addresses) {
                        addr.setDefault(false);
                    }
                }
                addresses.set(i, address);
                return true;
            }
        }
        return false;
    }
    
    public boolean deleteAddress(String userId, String addressId) {
        ArrayList<Address> addresses = getUserAddresses(userId);
        return addresses.removeIf(addr -> addr.getId().equals(addressId));
    }
    
    public boolean setDefaultAddress(String userId, String addressId) {
        ArrayList<Address> addresses = getUserAddresses(userId);
        boolean found = false;
        
        for (Address addr : addresses) {
            if (addr.getId().equals(addressId)) {
                addr.setDefault(true);
                found = true;
            } else {
                addr.setDefault(false);
            }
        }
        
        return found;
    }
    
    public Address getDefaultAddress(String userId) {
        ArrayList<Address> addresses = getUserAddresses(userId);
        for (Address addr : addresses) {
            if (addr.isDefault()) {
                return addr;
            }
        }
        // Return first address if no default
        return addresses.isEmpty() ? null : addresses.get(0);
    }
    
    // ==================== REVIEW MANAGEMENT ====================
    
    public ArrayList<Review> getProductReviews(String productId) {
        if (!productReviews.containsKey(productId)) {
            productReviews.put(productId, new ArrayList<>());
        }
        return productReviews.get(productId);
    }
    
    public Review addReview(String productId, String userId, String userName, float rating, String comment) {
        ArrayList<Review> reviews = getProductReviews(productId);
        
        Review newReview = new Review(
            UUID.randomUUID().toString(),
            productId,
            userId,
            userName,
            rating,
            comment
        );
        
        reviews.add(0, newReview); // Add to beginning
        
        // Update product rating
        updateProductRating(productId);
        
        return newReview;
    }
    
    private void updateProductRating(String productId) {
        ArrayList<Review> reviews = getProductReviews(productId);
        if (reviews.isEmpty()) return;
        
        float totalRating = 0;
        for (Review review : reviews) {
            totalRating += review.getRating();
        }
        
        float avgRating = totalRating / reviews.size();
        
        Product product = getProductById(productId);
        if (product != null) {
            product.setRating(avgRating);
        }
    }
    
    public boolean hasUserReviewedProduct(String userId, String productId) {
        ArrayList<Review> reviews = getProductReviews(productId);
        for (Review review : reviews) {
            if (review.getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean canUserReviewProduct(String userId, String productId) {
        // Check if user has purchased this product
        ArrayList<Order> userOrders = getUserOrders(userId);
        for (Order order : userOrders) {
            if (order.getStatus().equals("delivered")) {
                for (CartItem item : order.getItems()) {
                    if (item.getProduct().getId().equals(productId)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    // ==================== ADMIN METHODS ====================
    
    public ArrayList<User> getAllUsers() {
        return new ArrayList<>(users);
    }
    
    public ArrayList<Review> getAllReviews() {
        ArrayList<Review> allReviews = new ArrayList<>();
        for (ArrayList<Review> reviews : productReviews.values()) {
            allReviews.addAll(reviews);
        }
        return allReviews;
    }
    
    public boolean deleteReview(String reviewId) {
        for (ArrayList<Review> reviews : productReviews.values()) {
            for (Review review : reviews) {
                if (review.getId().equals(reviewId)) {
                    reviews.remove(review);
                    // Update product rating after deleting review
                    updateProductRating(review.getProductId());
                    return true;
                }
            }
        }
        return false;
    }
}
