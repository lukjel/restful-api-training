package isa.hibernate.mapper;

import isa.hibernate.domain.Developer;
import isa.hibernate.dto.DeveloperFullDTO;
import isa.hibernate.dto.DeveloperShortDTO;
import isa.hibernate.util.JsonHelper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import java.util.stream.Collectors;

@Stateless
public class DeveloperMapper {

	@Inject
	private TaskMapper taskMapper;

	public DeveloperShortDTO toShort(Developer d) {
		DeveloperShortDTO dto = new DeveloperShortDTO(d.getId(), d.getFirstName(),d.getLastName(), d.getCity());
		return dto;
	}

	public JsonObject toShortJson(Developer d) {
		return JsonHelper.toJson(
			"id", d.getId(),
			"firstName", d.getFirstName(),
			"lastName", d.getLastName(),
			"city", d.getCity(),
			"activeTask", taskMapper.toShortJson(d.getActiveTask()));
	}

	public DeveloperFullDTO toFull(Developer d) {
		return new DeveloperFullDTO(
			d.getId(),
			d.getFirstName(),
			d.getLastName(),
			d.getCity(),
			this.taskMapper.toShort(d.getActiveTask()),
			d.getTasks().stream()
				.map(t -> taskMapper.toShort(t))
				.collect(Collectors.toSet())
		);
	}

	public JsonObject toFullJson(Developer dev) {
		if (dev == null) {
			return null;
		}
		return JsonHelper.toJson(
			"id", dev.getId(),
			"firstName", dev.getFirstName(),
			"lastName", dev.getLastName(),
			"city", dev.getCity(),
			"activeTask", taskMapper.toShortJson(dev.getActiveTask()),
			"tasks", JsonHelper.toArray(dev.getTasks().stream()
				.map(t -> taskMapper.toShortJson(t))
				.toArray()
			)
		);
	}

	public Developer toEntity(DeveloperShortDTO dto) {
		Developer dev = new Developer();
		dev.setId(dto.getId());
		dev.setFirstName(dto.getFirstName());
		dev.setLastName(dto.getLastName());
		dev.setCity(dto.getCity());
		return dev;
	}
}
