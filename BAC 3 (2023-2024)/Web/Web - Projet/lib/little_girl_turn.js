const Roles = require("./Roles");
const { EmbedBuilder, Colors} = require("discord.js");
const { StringSelectMenuBuilder, StringSelectMenuOptionBuilder, ActionRowBuilder } = require("@discordjs/builders")

const littleGirlTurn = (channelLittleGirl, userList) => {
    return new Promise(async resolve => {
        if (!Array.isArray(userList)) {
            return false;
        }

        const embedLittleGirl = new EmbedBuilder()
            .setTitle(Roles.LITTLE_GIRL)
            .setColor(Colors.Blue)
            .setDescription("Choisissez deux joueurs, si l'un d'eux est un Loup-Garou, nous vous le dirons")
            .setFooter({
                "text": "Vous avez 30 secondes pour voter.",
            });

        const alivePlayerList = [];
        for (let i = 0; i < userList.length; i++) {
            if (!userList[i].isDead) {
                alivePlayerList.push(userList[i]);
            }
        }

        const selectMenu = new StringSelectMenuBuilder()
            .setCustomId('littleGirl')
            .addOptions(alivePlayerList.map((user) => {
                return new StringSelectMenuOptionBuilder()
                    .setLabel(user.player.displayName)
                    .setValue(user.player.id)
            }))
            .setMinValues(2)
            .setMaxValues(2);

        const row = new ActionRowBuilder()
            .addComponents(selectMenu);

        const littleGirlId = userList.find(p => p.role === Roles.LITTLE_GIRL).player.id;

        const msg = await channelLittleGirl.send({content: `<@${littleGirlId}>`, embeds: [embedLittleGirl], components: [row]});

        const collector = channelLittleGirl.createMessageComponentCollector({
            filter: i => i.customId === 'littleGirl' && userList.find(p => p.role === Roles.LITTLE_GIRL && p.player.id === i.user.id),
            time: 30_000
        });

        let isLoup = false;
        collector.on('collect', async i => {
            collector.stop();

            alivePlayerList.forEach(user => {
                if (user.player.id === i.values[0] || user.player.id === i.values[1]) {
                    if (user.role === Roles.WEREWOLF) {
                        isLoup = true;
                    }
                }
            });

            const embedRep = new EmbedBuilder()
                .setTitle(Roles.LITTLE_GIRL)
                .setColor(Colors.Blue)
                .setDescription(isLoup ? "L'un des joueur est un Loup-Garou" : "Il n'y a pas de Loup-Garou ici");

            resolve();

            await i.update({embeds: [embedRep], components: []});
        });

        collector.on('end', async collected => {
            if (collected.size === 0) {
                msg.delete();

                const embed = new EmbedBuilder()
                    .setTitle(Roles.LITTLE_GIRL)
                    .setColor(Colors.Red)
                    .setDescription('Vous n\'avez pas répondu a temps, vous passez votre tour.');

                await channelLittleGirl.send({ embeds: [embed], components: [] });

                resolve();
            }
        });
    });
}

module.exports = littleGirlTurn;
