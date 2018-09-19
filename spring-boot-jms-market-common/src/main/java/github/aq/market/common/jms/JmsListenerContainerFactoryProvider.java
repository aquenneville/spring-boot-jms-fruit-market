package github.aq.market.common.jms;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;

@Component
public class JmsListenerContainerFactoryProvider {

	@Bean
<<<<<<< HEAD
	public JmsListenerContainerFactory<?> queueListenerFactory(ConnectionFactory connectionFactory,
=======
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
>>>>>>> 8c09f5362fec9c4677e317a88320840f48d842b1
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		ActiveMQConnectionFactory cf = (ActiveMQConnectionFactory) connectionFactory;
		RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
		redeliveryPolicy.setMaximumRedeliveries(0);
		cf.setRedeliveryPolicy(redeliveryPolicy);
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConcurrency(String.valueOf(Runtime.getRuntime().availableProcessors()));
<<<<<<< HEAD
		factory.setPubSubDomain(false);
=======
		factory.setCacheLevelName("CACHE_CONNECTION");
>>>>>>> 8c09f5362fec9c4677e317a88320840f48d842b1
		configurer.configure(factory, connectionFactory);
		return factory;
	}

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
<<<<<<< HEAD
        converter.setTypeIdPropertyName("_type");        
=======
        converter.setTypeIdPropertyName("_type");
>>>>>>> 8c09f5362fec9c4677e317a88320840f48d842b1
        return converter;
    }
}
