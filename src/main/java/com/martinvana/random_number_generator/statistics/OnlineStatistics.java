package com.martinvana.random_number_generator.statistics;

/**
 * See http://www.johndcook.com/standard_deviation.html
 * or Donald Knuth's Art of Computer Programming, Vol 2, page 232, 3rd edition
 */
public class OnlineStatistics extends AStatistics {

    private long m_n;
    private double m_oldM, m_newM, m_oldS, m_newS;

    public OnlineStatistics() {
        m_n = 0;
    }

    @Override
    public void push(double x) {
        m_n++;

        // See Knuth TAOCP vol 2, 3rd edition, page 232
        if (m_n == 1) {
            m_oldM = m_newM = x;
            m_oldS = 0.0;
        } else {
            m_newM = m_oldM + (x - m_oldM) / m_n;
            m_newS = m_oldS + (x - m_oldM) * (x - m_newM);

            // set up for next iteration
            m_oldM = m_newM;
            m_oldS = m_newS;
        }
    }

    @Override
    public void reset() {
        m_n = 0;
    }

    @Override
    public double getMean() {
        return (m_n > 0) ? m_newM : 0.0;
    }

    @Override
    public double getVariance() {
        return ( (m_n > 1) ? m_newS/(m_n - 1) : 0.0 );
    }
}
