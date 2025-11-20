module order.base {
    exports products;
    exports order;
    exports factory;

    requires static lombok;
    requires javafaker;
    requires java.sql;
}