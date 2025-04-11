package dao;
import entity.Policy;
import exception.PolicyNotFoundException;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PolicyServiceImpl implements IPolicyService {

    @Override
    public boolean createPolicy(Policy policy) {
        String query = "INSERT INTO policies (policyName, coverageAmount) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, policy.getPolicyName());
            stmt.setDouble(2, policy.getCoverageAmount());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Policy getPolicy(int policyId) {
        String query = "SELECT * FROM policies WHERE policyId = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, policyId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Policy(rs.getInt("policyId"), rs.getString("policyName"), rs.getDouble("coverageAmount"));
            } else {
                throw new PolicyNotFoundException("Policy not found with ID: " + policyId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Policy> getAllPolicies() {
        String query = "SELECT * FROM policies";
        List<Policy> policies = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                policies.add(new Policy(rs.getInt("policyId"), rs.getString("policyName"), rs.getDouble("coverageAmount")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return policies;
    }

    @Override
    public boolean updatePolicy(Policy policy) {
        String query = "UPDATE policies SET policyName = ?, coverageAmount = ? WHERE policyId = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, policy.getPolicyName());
            stmt.setDouble(2, policy.getCoverageAmount());
            stmt.setInt(3, policy.getPolicyId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePolicy(int policyId) {
        String query = "DELETE FROM policies WHERE policyId = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, policyId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


