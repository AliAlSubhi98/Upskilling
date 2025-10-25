package com.upskilling.smartdeploymonitor.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * Security Validation Utilities
 * Provides comprehensive input validation and sanitization
 */
@Component
public class SecurityValidation {

    // Common attack patterns
    private static final Pattern SQL_INJECTION_PATTERN = Pattern.compile(
        "(?i)(union|select|insert|update|delete|drop|create|alter|exec|execute|script|javascript|vbscript|onload|onerror|onclick)"
    );
    
    private static final Pattern XSS_PATTERN = Pattern.compile(
        "(?i)(<script|</script|javascript:|vbscript:|onload|onerror|onclick|onmouseover|onfocus|onblur|onchange|onsubmit|onreset|onselect|onkeydown|onkeyup|onkeypress)"
    );
    
    private static final Pattern PATH_TRAVERSAL_PATTERN = Pattern.compile(
        "(\\.\\./|\\.\\.\\\\|%2e%2e%2f|%2e%2e%5c|%252e%252e%252f|%252e%252e%255c)"
    );
    
    private static final Pattern LDAP_INJECTION_PATTERN = Pattern.compile(
        "(?i)(\\*|\\(|\\)|\\\\|/|;|,|&|\\||!|=|<|>|~)"
    );
    
    // Email validation pattern
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );
    
    // Strong password pattern (at least 8 chars, 1 uppercase, 1 lowercase, 1 digit, 1 special char)
    private static final Pattern STRONG_PASSWORD_PATTERN = Pattern.compile(
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
    );

    /**
     * Validate and sanitize input string
     */
    public String sanitizeInput(String input) {
        if (!StringUtils.hasText(input)) {
            return "";
        }
        
        // Remove null bytes
        input = input.replaceAll("\0", "");
        
        // Trim whitespace
        input = input.trim();
        
        // Remove control characters
        input = input.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");
        
        return input;
    }

    /**
     * Check for SQL injection attempts
     */
    public boolean isSqlInjectionSafe(String input) {
        if (!StringUtils.hasText(input)) {
            return true;
        }
        
        return !SQL_INJECTION_PATTERN.matcher(input).find();
    }

    /**
     * Check for XSS attempts
     */
    public boolean isXssSafe(String input) {
        if (!StringUtils.hasText(input)) {
            return true;
        }
        
        return !XSS_PATTERN.matcher(input).find();
    }

    /**
     * Check for path traversal attempts
     */
    public boolean isPathTraversalSafe(String input) {
        if (!StringUtils.hasText(input)) {
            return true;
        }
        
        return !PATH_TRAVERSAL_PATTERN.matcher(input).find();
    }

    /**
     * Check for LDAP injection attempts
     */
    public boolean isLdapInjectionSafe(String input) {
        if (!StringUtils.hasText(input)) {
            return true;
        }
        
        return !LDAP_INJECTION_PATTERN.matcher(input).find();
    }

    /**
     * Validate email format
     */
    public boolean isValidEmail(String email) {
        if (!StringUtils.hasText(email)) {
            return false;
        }
        
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Validate password strength
     */
    public boolean isStrongPassword(String password) {
        if (!StringUtils.hasText(password)) {
            return false;
        }
        
        return STRONG_PASSWORD_PATTERN.matcher(password).matches();
    }

    /**
     * Validate input length
     */
    public boolean isValidLength(String input, int maxLength) {
        if (!StringUtils.hasText(input)) {
            return true;
        }
        
        return input.length() <= maxLength;
    }

    /**
     * Comprehensive security validation
     */
    public SecurityValidationResult validateInput(String input, int maxLength) {
        SecurityValidationResult result = new SecurityValidationResult();
        
        if (!StringUtils.hasText(input)) {
            result.setValid(true);
            return result;
        }
        
        // Check length
        if (!isValidLength(input, maxLength)) {
            result.addError("Input exceeds maximum length of " + maxLength + " characters");
        }
        
        // Check for SQL injection
        if (!isSqlInjectionSafe(input)) {
            result.addError("Potential SQL injection detected");
        }
        
        // Check for XSS
        if (!isXssSafe(input)) {
            result.addError("Potential XSS attack detected");
        }
        
        // Check for path traversal
        if (!isPathTraversalSafe(input)) {
            result.addError("Potential path traversal attack detected");
        }
        
        // Check for LDAP injection
        if (!isLdapInjectionSafe(input)) {
            result.addError("Potential LDAP injection detected");
        }
        
        return result;
    }

    /**
     * Security validation result class
     */
    public static class SecurityValidationResult {
        private boolean valid = true;
        private java.util.List<String> errors = new java.util.ArrayList<>();
        
        public boolean isValid() {
            return valid && errors.isEmpty();
        }
        
        public void setValid(boolean valid) {
            this.valid = valid;
        }
        
        public void addError(String error) {
            this.valid = false;
            this.errors.add(error);
        }
        
        public java.util.List<String> getErrors() {
            return errors;
        }
        
        public String getErrorMessage() {
            return String.join("; ", errors);
        }
    }
}
