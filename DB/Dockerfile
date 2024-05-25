# Use the official MySQL image from Docker Hub
FROM mysql:latest

# Set environment variables (customize as needed)
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=botellas_amor
ENV MYSQL_PASSWORD=root
ENV LANG=C.UTF-8
# Copy your .sql script into the container
COPY script.sql /docker-entrypoint-initdb.d/

# Expose the MySQL port (optional, but useful for connecting externally)
EXPOSE 3306

# Start MySQL service
CMD ["mysqld"]
