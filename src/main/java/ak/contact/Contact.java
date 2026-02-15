package ak.contact;

/**
 * Represents a contact with a name, email, phone number, and additional info.
 */
public class Contact {
    private String name;
    private String email;
    private String phone;
    private String info;

    /**
     * Constructs a new Contact.
     *
     * @param name The name of the contact.
     * @param email The email of the contact.
     * @param phone The phone number of the contact.
     * @param info Additional info about the contact.
     */
    public Contact(String name, String email, String phone, String info) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.info = info;
    }

    /**
     * Gets the contact name.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the contact name.
     *
     * @param name The new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the contact email.
     *
     * @return The email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the contact email.
     *
     * @param email The new email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the contact phone.
     *
     * @return The phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the contact phone.
     *
     * @param phone The new phone number.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the contact info.
     *
     * @return The info.
     */
    public String getInfo() {
        return info;
    }

    /**
     * Sets the contact info.
     *
     * @param info The new info.
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Returns a string representation of the contact for file storage. Format:
     * C | Name | Email | Phone | Info
     *
     * @return The formatted string for file storage.
     */
    public String toFileString() {
        return "C | " + name + " | " + email + " | " + phone + " | " + info;
    }

    @Override
    public String toString() {
        return String.format("Name: %s | Email: %s | Phone: %s | Info: %s", name, email, phone, info);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Contact contact = (Contact) obj;
        return name.equalsIgnoreCase(contact.name);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name.toLowerCase());
    }
}
